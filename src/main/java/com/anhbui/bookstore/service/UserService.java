package com.anhbui.bookstore.service;

import com.anhbui.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> getAllUserOrderByCreatedDate(Pageable pageable) ;

    User getUserById(Long userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long userId);

    boolean registerUser(User user);

    void deleteUserById(Long id);

    void saveUser(User user);
    void addBookToUser(Long userId, Long BookId);
    void removeBookFromUser(Long userId, Long BookId) ;

    Long countUser();
}
