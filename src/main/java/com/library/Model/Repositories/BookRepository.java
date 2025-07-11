package com.library.Model.Repositories;

import com.library.Model.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public @Repository interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    Book findByPatrimonialId(Long id);
}
