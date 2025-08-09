package com.library.Services;

import com.library.Model.DTO.ApiDataResponse;
import com.library.Model.DTO.Pagination;
import com.library.Model.Entities.Book;
import com.library.Model.Entities.BorrowedBook;
import com.library.Model.Entities.Student;
import com.library.Model.Entities.User;
import com.library.Model.Enums.Activity;
import com.library.Model.Repositories.BorrowedBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BorrowedBookService {

    @Autowired
    ObjectMapper objectMapper;

    BorrowedBookRepository borrowedBookRepository;
    BookService bookService;
    StudentService studentService;
    LogService logService;
    DateService dateService;
    UserService userService;
    Pageable pageable = PageRequest.of(0, 10);

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
    public ApiDataResponse<BorrowedBook> getBorrowedBookList(int page) {
        Page<BorrowedBook> borrowedBooks = borrowedBookRepository.findAll(pageable);
        Pagination pagination = new Pagination(page, (int) borrowedBooks.getTotalElements());
        return new ApiDataResponse<BorrowedBook>(borrowedBooks.getContent(), pagination);
    }

    public String stringBookList(ApiDataResponse<BorrowedBook> borrowedBookApiDataResponse) throws JsonProcessingException {
                return objectMapper.writeValueAsString(borrowedBookApiDataResponse);
    }

    public Optional<BorrowedBook> getBorrowedBook(Long bookId, Long studentId) throws JsonProcessingException {
        return borrowedBookRepository.findByStudentIdAndBookId(studentId, bookId);
    }

    public ApiDataResponse<BorrowedBook> getBorrowedBookById(Long bookId) throws JsonProcessingException {
        Pagination pagination = new Pagination(0, 1);
        return new ApiDataResponse<BorrowedBook>(Collections.singletonList(borrowedBookRepository.findById(bookId).orElseThrow()), pagination);
    }

    public String borrowedBookAction(Long bookId, Long studentId) throws JsonProcessingException {
        User user = userService.getUserByCred("admin", "123456");
        Book book = bookService.getBookById(bookId).getRows().get(0);
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
