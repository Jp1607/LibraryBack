package com.library.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library.Model.DTO.BookDTO;
import com.library.Model.Enums.Activity;
import com.library.Model.Entities.Book;
import com.library.Model.Entities.User;
import com.library.Model.Repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    LogService logService;
    ObjectMapper objectMapper;
    BookRepository bookRepository;
    DateService dateService;

    @Autowired
    public BookService(LogService logService, ObjectMapper objectMapper, BookRepository bookRepository, DateService dateService) {
        this.logService = logService;
        this.objectMapper = objectMapper;
        this.bookRepository = bookRepository;
        this.dateService = dateService;
    }

    public String getBookByTitle(String title) {
        try {
            List<Book> bookList = bookRepository.findByTitleContaining(title);
            bookList = bookList.stream().filter(book -> !book.getExcluded()).toList();
            return objectMapper.writeValueAsString(bookList);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Book getBookById(Long id) {
        try {
            return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        } catch (Exception e) {
            throw e;
        }
    }

    public String getStringfiedBookById(Long id) {
        try {
            Book book =  bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
            return objectMapper.writeValueAsString(book);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookRepository.findAll());
    }

    //Should have error handling
    public Book getBookByPatrimonialId(Long id) {
        return bookRepository.findByPatrimonialId(id);
    }


    public void addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        bookRepository.save(book);
        logService.newLog("books", book.getId(), book, null, Activity.NEW);
    }

    public void editBook(Book book) {
        Book returnBook = bookRepository.save(book);
        logService.newLog("books", returnBook.getId(), returnBook, null, Activity.EDIT);
    }

    public void excludeBook(Long id) {
        Book tempBook = bookRepository.findById(id).get();
        tempBook.setExcluded(true);
        bookRepository.save(tempBook);
        logService.newLog("books", tempBook.getId(), tempBook, null, Activity.REMOVE);
    }

    public void markAsBorrowed(Book book) {
        book.setAvailable(false);
        bookRepository.save(book);
    }

    public void markAsReturned(Book book) {
        book.setAvailable(true);
        bookRepository.save(book);
    }
}
