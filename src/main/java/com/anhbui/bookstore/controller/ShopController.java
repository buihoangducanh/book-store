package com.anhbui.bookstore.controller;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.dto.UserSearchDTO;
import com.anhbui.bookstore.entity.Book;
import com.anhbui.bookstore.entity.Category;
import com.anhbui.bookstore.service.BookService;
import com.anhbui.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

    private CategoryService categoryService;
    private BookService bookService;

    @GetMapping
    public String getShopPage(
            @ModelAttribute("searchModel") UserSearchDTO searchModel,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        Pageable pageable = PageRequest.of(page - 1, 6);

        Page<Book> searchResult;

        if (searchModel.isEmpty()) {
            searchResult = bookService.getAllBooksForUsers(pageable);
        } else {
            searchResult = bookService.searchBooksUser(searchModel, pageable);
        }

        model.addAttribute("books", searchResult);
        model.addAttribute("totalPages", searchResult.getTotalPages());
        model.addAttribute("currentPage", searchResult.getNumber());
        model.addAttribute("sortBy", searchModel.getSortBy());
        model.addAttribute("sortBy", searchModel.getCategoryId());

        return "user/shop";
    }

    @GetMapping("/product/{product_id}")
    public String viewProductDetail(@PathVariable Long product_id, Model model) {
        Book product = bookService.getBookById(product_id);
        model.addAttribute("product", product);
        return "user/product_details";
    }
}
