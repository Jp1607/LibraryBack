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

    BorrowedBookRepository borrowedBookRepository;
    BookService bookService;
    StudentService studentService;
    LogService logService;
    DateService dateService;
    UserService userService;

    @Autowired
    public BorrowedBookService(BorrowedBookRepository borrowedBookRepository, BookService bookService, StudentService studentService, LogService logService, DateService dateService, UserService userService) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookService = bookService;
        this.studentService = studentService;
        this.logService = logService;
        this.dateService = dateService;
        this.userService = userService;
    }

    //Will implement sorting
    public List<BorrowedBook> getBorrowedBookList() {
        return borrowedBookRepository.findAll();
    }

    public String getStringfiedBookList() throws JsonProcessingException {
        return objectMapper.writeValueAsString(borrowedBookRepository.findAll());
    }

    public Optional<BorrowedBook> getBorrowedBook(Long bookId, Long studentId) throws JsonProcessingException {
        return borrowedBookRepository.findByStudentIdAndBookId(studentId, bookId);
    }

    public String borrowedBookAction(Long bookId, Long studentId) throws JsonProcessingException {
        User user = userService.getUserByCred("admin", "123456");
        Book book = bookService.getBookById(bookId);
        Student student = studentService.getById(studentId);
        LocalDateTime localDateTime = dateService.getCurrentDate();
        if (getBorrowedBook(bookId, studentId).isPresent()) {
            BorrowedBook borrowedBook = getBorrowedBook(bookId, studentId).get();
            borrowedBookRepository.delete(borrowedBook);
            bookService.markAsReturned(book);
            logService.newLog("book_flow", book.getId(), book, student, Activity.RETURN);
            return "Livro devolvido com sucesso!";
        } else {
            BorrowedBook borrowedBook = new BorrowedBook(book, student, user, localDateTime);
            borrowedBookRepository.save(borrowedBook);
            bookService.markAsBorrowed(book);
            logService.newLog("book_flow", book.getId(), book, student, Activity.BORROW);
            return "Livro emprestado com sucesso!";
        }
    }
}
