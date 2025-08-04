
package io.github.user21spl.booksnotfound404.controller;


import io.github.user21spl.booksnotfound404.model.*;
import io.github.user21spl.booksnotfound404.dao.*;
import io.github.user21spl.booksnotfound404.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@Controller
public class HomeController {
    

    @Autowired
    private ExternalBookService externalBookService;
    
    @Autowired
    private BookService bookService;
    

    @GetMapping("/")
    public String showHomePage(Model model) {
       List<ExternalBookService.ExternalBook> bestSellers = externalBookService.fetchBestSellingBooks();
        List<String> genres = bookService.getAllGenres();

        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("genres", genres);

        return "home"; 
    }
 
}
