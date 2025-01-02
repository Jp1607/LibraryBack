package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public @Repository interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    Book findByPatrimonialId(Long id);
}
