package com.example.demo.Services;

import com.example.demo.Entities.Book;
import com.example.demo.Entities.BorrowedBook;
import com.example.demo.Entities.Student;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowedBookService {

    @Autowired
    BorrowedBookRepository borrowedBookRepository;

    public void newBorrowedBook(Book book, Student student, User user, Date date) {
        BorrowedBook borrowedBook = new BorrowedBook(book, student, user, date);
        borrowedBookRepository.save(borrowedBook);
    }
}
