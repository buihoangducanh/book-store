package com.anhbui.bookstore.controller.admin;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.dto.BookSearchDTO;
import com.anhbui.bookstore.entity.Book;
import com.anhbui.bookstore.entity.Category;

import com.anhbui.bookstore.service.BookService;
import com.anhbui.bookstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/books_management")
public class AdminBookController extends BaseController {

    private final BookService bookService;
    private final CategoryService categoryService;


    @GetMapping
    public String showBooksPage(Model model,
                                @RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "size", defaultValue = "4") int size,
                                @ModelAttribute("search") BookSearchDTO search) {
        Page<Book> bookPage = bookService.searchBooks(search, PageRequest.of(page - 1, size));
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("bookPage", bookPage);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());

        return "admin/books";
    }


    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "admin/books_add_or_update";
    }

    @PostMapping("/add")
    public String addOrUpdateBook(@ModelAttribute("book") @Valid Book book,
                                  BindingResult bindingResult,
                                  @RequestParam("cover_image") MultipartFile coverImage,
                                  Model model
    ,RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("error", "Đã có lỗi xảy ra vui lòng nhập lại");
            return "admin/books_add_or_update";
        }


        if (book.getId() != null) {
            // Check if there is an existing book with the given ID
            Book existingBook = bookService.getBookById(book.getId());
            if (existingBook != null) {
                // Update the book with new data
                if (book.getPublishedDate() == null) {
                    book.setPublishedDate(existingBook.getPublishedDate());
                }
                if (coverImage.isEmpty()) {
                    book.setCoverImage(existingBook.getCoverImage());
                }

                bookService.editBook(book, coverImage);
                Book editedBook = bookService.getBookById(book.getId());
                model.addAttribute("book",editedBook);
                redirectAttributes.addFlashAttribute("message","Sửa thông tin sách thành công!");
            }
        } else {
            Book exist = bookService.getBookByName(book.getTitle());

            if (exist!= null) {
                model.addAttribute("error", "Đã tồn tại sách với tên này");
                return "admin/books_add_or_update";
            }else bookService.addBook(book, coverImage);
            redirectAttributes.addFlashAttribute("message","Thêm sách thành công!");
        }

        return "redirect:/admin/books_management/add";
    }



    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("book", book);
        model.addAttribute("categories", categories);

        return "admin/books_add_or_update";
    }


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);

        // Add a success message to the model
        redirectAttributes.addFlashAttribute("message", "Xoá sách thành công!");

        return "redirect:/admin/books_management";
    }

}
