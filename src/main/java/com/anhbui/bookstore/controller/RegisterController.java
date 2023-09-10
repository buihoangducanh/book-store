package com.anhbui.bookstore.controller;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.entity.User;
import com.anhbui.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String showRegistrationForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user
    ,Model model) {
        if (userService.registerUser(user)) {
            model.addAttribute("user", new User());
            model.addAttribute("success",true);
        } else {
            model.addAttribute("error",true);
        }
        return "user/register";
    }


}
