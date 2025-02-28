package com.library.Services;

import com.library.Model.Entities.Book;
import com.library.Model.Entities.BorrowedBook;
import com.library.Model.Entities.Student;
import com.library.Model.Entities.User;
import com.library.Model.Enums.Activity;
import com.library.Model.Enums.BookFlowAction;
import com.library.Model.Repositories.BorrowedBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowedBookService {

    ObjectMapper objectMapper;

    @Autowired
    BorrowedBookRepository borrowedBookRepository;
    BookService bookService;
    StudentService studentService;
    LogService logService;
    DateService dateService;

    //Will implement sorting
    public List<BorrowedBook> getBorrowedBookList() {
        return borrowedBookRepository.findAll();
    }

    public String getStringfiedBookList() throws JsonProcessingException {
        return objectMapper.writeValueAsString(borrowedBookRepository.findAll());
    }

    public Optional<BorrowedBook> getBorrowedBook(Long bookId, Long studentId) throws JsonProcessingException {
        return borrowedBookRepository.findByUserIdAndBookId(bookId, studentId);
    }

    public void borrowedBookAction(Long bookId, Long studentId, User user) throws JsonProcessingException {
        Book book = bookService.getBookByPatrimonialId(bookId);
        Student student = studentService.getById(studentId);
        LocalDateTime localDateTime = dateService.getCurrentDate();
        if (getBorrowedBook(bookId, studentId).isPresent()) {
            BorrowedBook borrowedBook = getBorrowedBook(bookId, studentId).get();
            borrowedBookRepository.delete(borrowedBook);
            logService.newLog("book_flow", book.getId(), book, student, Activity.RETURN);
        } else {
            BorrowedBook borrowedBook = new BorrowedBook(book, student, user, localDateTime);
            borrowedBookRepository.save(borrowedBook);
            logService.newLog("book_flow", book.getId(), book, student, Activity.BORROW);
        }
    }
}
