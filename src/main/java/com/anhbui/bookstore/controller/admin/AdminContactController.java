package com.anhbui.bookstore.controller.admin;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.dto.Email;
import com.anhbui.bookstore.entity.Contact;
import com.anhbui.bookstore.service.ContactService;
import com.anhbui.bookstore.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/contacts")
public class AdminContactController extends BaseController {
    private final ContactService contactService;
    private final EmailService emailService;
    @GetMapping
    public String adminContacts(Model model,
                                @RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "sort_by",defaultValue = "newest") String sortBy){

        int pageSize = 4;
        Pageable pageable = PageRequest.of(page-1,pageSize);
        Page<Contact> contacts = contactService.getContactsPage(sortBy,pageable);
        model.addAttribute("contacts",contacts);
        model.addAttribute("totalPages",contacts.getTotalPages());
        model.addAttribute("currentPage",contacts.getNumber());
        model.addAttribute("sortBy",sortBy);

        return "/admin/contacts";
    }
    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id){
        contactService.deleteById(id);
        return "redirect:/admin/contacts";
    }

    @GetMapping("/response/{id}")
    public String response(@PathVariable Long id, Model model){
        String userEmail = contactService.getContactById(id).getEmail();
        Email email = new Email();
        email.setTo(userEmail);
        model.addAttribute("newEmail",email);
        model.addAttribute("uid",id);

        return "admin/contact_response";
    }
    @PostMapping("/submit_email")
    public String responseTo(@ModelAttribute Email email,
                             @RequestParam Long uid,
                             RedirectAttributes redirectAttributes){

        emailService.sendSimpleEmail(email.getTo(),email.getSubject(),email.getMessage());
        redirectAttributes.addFlashAttribute("message","Gửi mail thành công!");

        return "redirect:/admin/contacts/response/" + uid + "?success=true";
    }

}
