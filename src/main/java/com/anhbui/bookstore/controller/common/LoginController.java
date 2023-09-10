package com.anhbui.bookstore.controller.common;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @GetMapping
    public String getLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return "redirect:/home";
        }
        return "user/login";
    }
}
