package com.example.demo.Services;
import com.example.demo.Entities.Book;
import com.example.demo.Entities.BorrowedBook;
import com.example.demo.Entities.Student;
import com.example.demo.Entities.User;
import com.example.demo.Enums.Activity;
import com.example.demo.Repositories.BorrowedBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowedBookService {

    ObjectMapper objectMapper;

    @Autowired
    BorrowedBookRepository borrowedBookRepository;
    BookService bookService;
    LogService logService;

    //Will implement sorting
    public List<BorrowedBook> getBorrowedBookList(){
        return borrowedBookRepository.findAll();
    }

    public String getStringfiedBookList() throws JsonProcessingException {
        return objectMapper.writeValueAsString(borrowedBookRepository.findAll());
    }

    public void newBorrowedBook(Long bookId, Student student, User user, LocalDateTime localDateTime, Boolean isReturned) {
        Book book = bookService.getBookByPatrimonialId(bookId);
        BorrowedBook borrowedBook = new BorrowedBook(book, student, user, localDateTime, isReturned);
        borrowedBookRepository.save(borrowedBook);
        bookService.markAsBorrowed(book);
        logService.newLog(user, book, student, localDateTime, Activity.BORROW);
    }
}
