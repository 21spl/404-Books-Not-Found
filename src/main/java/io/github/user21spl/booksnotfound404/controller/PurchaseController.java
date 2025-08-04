package io.github.user21spl.booksnotfound404.controller;

import io.github.user21spl.booksnotfound404.model.*;
import io.github.user21spl.booksnotfound404.service.*;
import io.github.user21spl.booksnotfound404.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.time.*;

@Controller
public class PurchaseController {

    @Autowired 
    private BookService bookService;
    @Autowired 
    private CartService cartService;
    @Autowired 
    private PurchaseService purchaseService;
    @Autowired 
    private CartItemDao cartItemDao;
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private PurchaseItemDao purchaseItemDao;
    @Autowired
    private BookDao bookDao;
    

    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String keyword, Model model) {
        List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/books")
    public String exploreGenre(@RequestParam("genre") String genre, Model model) {
        List<Book> booksByGenre = bookService.searchBooksByGenre(genre);
        model.addAttribute("genre", genre);
        model.addAttribute("books", booksByGenre);
        return "book_list";
    }
    
    
    //============================================================================================================
    
    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam("bookId") int bookId,
            @RequestParam("quantity") int quantity,
            HttpSession session) {
        // Check if user is logged in
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("Please login to add items to cart.");
        }

        // Validate quantity
        if (quantity <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Quantity must be at least 1.");
        }

        // Fetch book
        Book book = bookDao.findBook(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Book with ID " + bookId + " not found.");
        }
        
        

        // Check copies availability
        Integer copies = book.getCopies();
        if (copies == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("This book is out of stock.");
        }
        if (copies < quantity) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Only " + copies + " copies available.");
        }

        try {
            // Add to cart
            cartService.addBookToCart(user, book, quantity);
            return ResponseEntity.ok("Book '" + book.getTitle() + "' added to cart successfully!");
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to add book to cart: " + e.getMessage());
        }
    }
    
    //==============================================================================
    

    @GetMapping("/cart")
    public String viewCartPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        Cart cart = cartService.getCartByUser(user);
        List<CartItem> cartItems = cart != null ? cartService.getCartItems(user) : new ArrayList<>();

        double totalAmount = 0;
        boolean hasOutOfStock = false;
        for (CartItem item : cartItems) {
            totalAmount += item.getQuantity() * item.getBook().getPrice();
            if (item.getQuantity() > item.getBook().getCopies()) {
                hasOutOfStock = true;
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("hasOutOfStock", hasOutOfStock);
        return "cart";
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public ResponseEntity<?> updateCartItemQuantity(@RequestParam int itemId, @RequestParam int quantity, HttpSession session) {
        
        // check whether user is logged in or not
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("Please login to access the cart.");
        }
        
        CartItem item = cartItemDao.findById(itemId);
        if (item == null) return ResponseEntity.badRequest().body("Invalid cart item");
        
        // validate quantity
        int available = item.getBook().getCopies();

        if (quantity < 1) {
            cartService.removeCartItem(item);
            return ResponseEntity.ok("Item removed due to zero quantity");
        } else if (quantity > available) {
            return ResponseEntity.badRequest().body("Only " + available + " copies available.");
        } else {
            item.setQuantity(quantity);
            cartItemDao.updateCartItem(item);
            return ResponseEntity.ok("Quantity updated");
        }
        
    }

    @ResponseBody
    @PostMapping("/cart/remove")
    public ResponseEntity<?> removeCartItem(@RequestParam int itemId, HttpSession session) {
        
        // check whether user is logged in or not
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.badRequest()
                                .body("Please login to access the cart.");
        }
        
        
        CartItem item = cartItemDao.findById(itemId);
        if (item == null) {
            return ResponseEntity.badRequest().body("Invalid Cart Item");
        }
        
        // remove item
        cartItemDao.removeCartItem(item);
        return ResponseEntity.ok("Item removed from cart");
    }

    @GetMapping("/checkout")
    public String showCheckOutForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null){
            return "redirect:/login";
        }

        Cart cart = cartService.getCartByUser(user);
        List<CartItem> cartItems = cartItemDao.findItemsByCartId(cart.getCartId());

        List<String> outOfStockMessages = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem item : cartItems) {
            totalAmount += item.getQuantity() * item.getBook().getPrice();
            if (item.getQuantity() > item.getBook().getCopies()) {
                outOfStockMessages.add(item.getBook().getTitle() +
                        " is out of stock (Available: " + item.getBook().getCopies() + ")");
            }
        }

        if (!outOfStockMessages.isEmpty()) {
            model.addAttribute("outOfStockWarnings", outOfStockMessages);
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalAmount", totalAmount);
            return "cart";
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "checkout";
    }
    
    
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam("address") String address,
                             @RequestParam("phone") String phone,
                             HttpSession session,
                             Model model) {

        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        Cart cart = cartService.getCartByUser(currentUser);
        List<CartItem> cartItems = cartItemDao.findItemsByCartId(cart.getCartId());

        // Step 1: Check stock
        List<String> stockErrors = new ArrayList<>();
        for (CartItem item : cartItems) {
            Book book = item.getBook();
            if (item.getQuantity() > book.getCopies()) {
                stockErrors.add(book.getTitle() + " (Available: " + book.getCopies() + ")");
            }
        }

        if (!stockErrors.isEmpty()) {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalAmount", cartItems.stream().mapToDouble(i -> i.getQuantity() * i.getBook().getPrice()).sum());
            model.addAttribute("stockErrors", stockErrors);
            return "checkout";
        }

        // Step 2: Create Purchase
        Purchase purchase = new Purchase();
        purchase.setUser(currentUser);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setAddress(address);
        purchase.setPhone(phone);
        purchase.setTotalAmount(cartItems.stream().mapToDouble(i -> i.getQuantity() * i.getBook().getPrice()).sum());
        

        purchaseDao.savePurchase(purchase); // Save to generate ID

        // Step 3: Create PurchaseItems + update stock
        for (CartItem item : cartItems) {
            Book book = item.getBook();
            book.setCopies(book.getCopies() - item.getQuantity());
            bookService.updateBook(book); // assuming updateBook() method exists

            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setBook(book);
            purchaseItem.setQuantity(item.getQuantity());
            purchaseItem.setPurchase(purchase);
            purchaseItem.setPriceAtPurchase(book.getPrice());

            purchaseItemDao.savePurchaseItem(purchaseItem);
            
            cartItemDao.removeCartItem(item);
        }

        // Step 4: Clear cart
        
        cartService.clearCart(currentUser);

        // Step 5: Redirect to success page
        return "redirect:/order_success";
    }
    
    @GetMapping("/order_success")
    public String showOrderSuccessPage() {
        return "order_success"; 
    }
 
}
