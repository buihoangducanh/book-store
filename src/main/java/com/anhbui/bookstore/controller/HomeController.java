package com.anhbui.bookstore.controller;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.entity.Book;
import com.anhbui.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController extends BaseController {

    private BookService bookService;

    @GetMapping("/")
    String getUserHomePage(Model model){

        List<Book> top4BestSeller =bookService.getTop4BestSeller();
        model.addAttribute("top4BestSeller",top4BestSeller);
        List<Book> newProducts =bookService.findAllOrderByCreatedDate();
        model.addAttribute("newProducts",newProducts);
        return "user/index";
    }
}
