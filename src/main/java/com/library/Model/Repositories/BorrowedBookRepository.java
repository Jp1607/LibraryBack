package com.library.Model.Repositories;

import com.library.Model.Entities.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public @Repository interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
}
