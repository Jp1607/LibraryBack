package com.library.Services;
import com.library.Model.Entities.Book;
import com.library.Model.Entities.BorrowedBook;
import com.library.Model.Entities.Student;
import com.library.Model.Entities.User;
import com.library.Model.Enums.Activity;
import com.library.Model.Repositories.BorrowedBookRepository;
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
