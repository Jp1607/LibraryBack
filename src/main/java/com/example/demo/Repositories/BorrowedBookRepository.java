package com.example.demo.Repositories;

import com.example.demo.Entities.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public @Repository interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
}
