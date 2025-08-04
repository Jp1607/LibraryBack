package com.library.Model.Repositories;

import com.library.Model.Entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public @Repository interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Book findByPatrimonialId(Long id);
    Page<Book> findById(Long id, Pageable pageable);

}
