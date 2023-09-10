package com.anhbui.bookstore.service;

import com.anhbui.bookstore.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    Contact saveContact(Contact contact);

    Page<Contact> getContactsPage(String sortBy, Pageable pageable);

    void deleteById(Long id);

    Contact getContactById(Long id);
}
