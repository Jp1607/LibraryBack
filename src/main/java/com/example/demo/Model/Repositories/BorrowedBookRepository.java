package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public @Repository interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
}
