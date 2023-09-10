package com.anhbui.bookstore.repository;


import com.anhbui.bookstore.entity.Book;
import com.anhbui.bookstore.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(String keyword, Pageable pageable);
    Book findByTitle(String title);

    Page<Book> findByCategory(Category category, Pageable pageable);


    Page<Book> findByCategory_IdAndTitleContaining(Long categoryId, String keyword, Pageable pageable);

    Page<Book> findByCategoryIdAndTitleContainingOrderBySalePriceDesc(Long categoryId, String keyword, Pageable pageable);
    Page<Book> findByCategoryIdAndTitleContainingOrderBySalePriceAsc(Long categoryId, String keyword, Pageable pageable);
    Page<Book> findByCategoryIdAndTitleContainingOrderByCreatedAtAsc(Long categoryId, String keyword, Pageable pageable);
    Page<Book> findByCategoryIdAndTitleContainingOrderByCreatedAtDesc(Long categoryId, String keyword, Pageable pageable);


    Page<Book> findByTitleContainingOrderByCreatedAtAsc(String title, Pageable pageable);
    Page<Book> findByTitleContainingOrderByCreatedAtDesc(String title, Pageable pageable);
    Page<Book> findByTitleContainingOrderBySalePriceAsc(String title, Pageable pageable);
    Page<Book> findByTitleContainingOrderBySalePriceDesc(String title, Pageable pageable);


    List<Book> findTop4ByOrderByBuyCountAsc();
    List<Book> findByOrderByCreatedAtDesc();
}
