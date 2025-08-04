
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

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user", new User());
        return "register"; // corresponds to register.html
    }
    
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "register"; // will display validation messages
        }
        
        user.setRole("CUSTOMER");
        boolean success = userService.registerUser(user);
        if(!success)
        {
            model.addAttribute("error", "Email already registered. Please Login.");
            return "redirect:/login";
        }
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String showLoginForm(){
        return "login"; // return login.html
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email, 
            @RequestParam("password") String password, 
            Model model, 
            HttpSession session)
    {
        LoginResult result = userService.authenticate(email, password);
        
        if(!result.isSuccess()){
            model.addAttribute("error", result.getMessage());
            return "login"; //back to login with error
        }
        // if login is successful, we store the user in the session (optional)
        User loggedInUser = result.getUser();
        session.setAttribute("loggedInUser", loggedInUser);
        
        // check if logged-in user is admin
        if("ADMIN".equalsIgnoreCase(loggedInUser.getRole()))
        {
            return "redirect:/admin/dashboard";
        }
        
        //model.addAttribute("message", "Welcome, " + result.getUser().getName() + "!");
        return "redirect:/"; 
    }
    
    
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("currentUser");
        String firstName = "";
        if (user != null && user.getName() != null) {
            firstName = user.getName().split(" ")[0];
        }
        model.addAttribute("firstName", firstName);
        session.removeAttribute("currentUser");
        session.invalidate();
        return "logout";
        
    }
}
