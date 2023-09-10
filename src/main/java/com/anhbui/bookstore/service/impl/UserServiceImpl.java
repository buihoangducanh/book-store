package com.anhbui.bookstore.service.impl;

import com.anhbui.bookstore.entity.Book;
import com.anhbui.bookstore.entity.Role;
import com.anhbui.bookstore.entity.User;
import com.anhbui.bookstore.repository.BookRepository;
import com.anhbui.bookstore.repository.RoleRepository;
import com.anhbui.bookstore.repository.UserRepository;
import com.anhbui.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getAllUserOrderByCreatedDate(Pageable pageable) {
        return userRepository.findAllByOrderByCreatedAtDesc(pageable);
    }


    @Override
    public void addBookToUser(Long userId, Long BookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(BookId).orElse(null);

        if (user != null && book != null) {
            user.addFavoriteBook(book);
            userRepository.save(user);
        }
    }
    @Override
    public void removeBookFromUser(Long userId, Long BookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(BookId).orElse(null);

        if (user != null && book != null) {
            user.removeFavoriteBook(book);
            userRepository.save(user);
        }
    }

    @Override
    public Long countUser() {
        return userRepository.count();
    }


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        user.getRoles().add(userRole);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
