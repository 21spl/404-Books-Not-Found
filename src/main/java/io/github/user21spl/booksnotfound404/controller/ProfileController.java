
package io.github.user21spl.booksnotfound404.controller;


import io.github.user21spl.booksnotfound404.model.*;
import io.github.user21spl.booksnotfound404.dao.*;
import io.github.user21spl.booksnotfound404.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;


// session tracking
import javax.servlet.http.HttpSession;

import java.util.*;


@Controller
public class ProfileController {
    
    @Autowired
    private PurchaseService purchaseService;

    // on clicking profile from navbar
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session)
    {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        
        List<Purchase> userPurchases = purchaseService.findAllPurchaseByUserId(loggedInUser.getUserId());

        model.addAttribute("user", loggedInUser);
        model.addAttribute("purchases", userPurchases);

        return "profile";
    }
    
    
    
    
    
    // show all purchases
    @GetMapping("/profile/purchase_history")
    public String viewAllPurchases(HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("loggedInUser");
        if(user==null)
        {
            return "redirect:/login";
        }
        
        List<Purchase> purchases = purchaseService.findAllPurchaseByUserId(user.getUserId());
        model.addAttribute("purchases", purchases);
        return "purchase_history"; // takes me to the purchase history page
    }
    
    // show purchase details of a single purchase
    @GetMapping("profile/purchase_details/{id}")
    public String viewPurchaseDetails(@PathVariable("id") int purchaseId, 
            HttpSession session,
            Model model)
    {
        User user = (User) session.getAttribute("loggedInUser");
        if(user==null)
        {
            return "redirect:/login";
        }
        
        Purchase purchase = purchaseService.findByIdWithItems(purchaseId);
        if(purchase==null){
            return "redirect:/error500";
        }
        
        model.addAttribute("loggedInUser", user); 
        model.addAttribute("purchase", purchase);
        return "purchase_details";
    }

    
    
}
