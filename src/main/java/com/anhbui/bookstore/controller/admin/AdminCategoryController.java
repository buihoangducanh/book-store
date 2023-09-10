package com.anhbui.bookstore.controller.admin;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.entity.Category;
import com.anhbui.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/categories_management")
public class AdminCategoryController extends BaseController {
    private final CategoryService categoryService;

    @GetMapping()
    public String getAllCategories(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5; // Kích thước trang bạn muốn hiển thị
        Page<Category> categoryPage = categoryService.getAllCategories(PageRequest.of(page-1, pageSize));

        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", categoryPage.getNumber());
        model.addAttribute("totalPages", categoryPage.getTotalPages());

        return "admin/category";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category_add_or_update";
    }

    @PostMapping("/add_or_update")
    public String addOrUpdateCategory(@ModelAttribute Category category
    , RedirectAttributes redirectAttributes) {
        if (category.getId() != null) {
            // Có ID -> Cập nhật
            categoryService.updateCategory(category.getId(), category);
            redirectAttributes.addFlashAttribute("message","Cập nhật danh mục thành công");

        } else {
            // Không có ID -> Thêm mới
            categoryService.addCategory(category);
            redirectAttributes.addFlashAttribute("message","Thêm mới danh mục thành công");
        }
        return "redirect:/admin/categories_management";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(Model model,@PathVariable Long id) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/category_add_or_update";
    }
    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId,
                                 RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(categoryId);
        redirectAttributes.addFlashAttribute("message","Xoá danh mục thành công");

        return "redirect:/admin/categories_management";
    }
}