package com.library.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library.Model.DTO.ApiDataResponse;
import com.library.Model.DTO.BookDTO;
import com.library.Model.DTO.Pagination;
import com.library.Model.Enums.Activity;
import com.library.Model.Entities.Book;
import com.library.Model.Repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

      LogService logService;
      ObjectMapper objectMapper;
      BookRepository bookRepository;
      DateService dateService;
      Pageable pageable = PageRequest.of(0, 10);

      @Autowired
      public BookService(LogService logService, ObjectMapper objectMapper, BookRepository bookRepository, DateService dateService) {
            this.logService = logService;
            this.objectMapper = objectMapper;
            this.bookRepository = bookRepository;
            this.dateService = dateService;
      }

      public ApiDataResponse<Book> getBookByTitle(String title, int pageNumber) {
            try {
                  Page<Book> page = bookRepository.findByTitleContaining(title, pageable.withPage(pageNumber));
                  List<Book> bookList = page.getContent();
                  Pagination pagination = new Pagination(pageNumber, (int) page.getTotalElements());
                  return new ApiDataResponse<Book>(bookList, pagination);
            } catch (Exception e) {
                  throw e;
            }
      }

      public ApiDataResponse<Book> getBookById(Long id) {
            try {
                  Book book = bookRepository.findById(id).orElseThrow();
                  Pagination pagination = new Pagination(0, 1);
                  return new ApiDataResponse<Book>(Collections.singletonList(book), pagination);
            } catch (Exception e) {
                  throw e;
            }
      }

      public String getStringfiedBookById(Long id) {
            try {
                  Book book = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
                  return objectMapper.writeValueAsString(book);
            } catch (Exception e) {
                  return e.getMessage();
            }
      }

      public ApiDataResponse<Book> getAll(int pageNumber) throws JsonProcessingException {
            Page<Book> page = bookRepository.findAll(pageable.withPage(pageNumber));
            List<Book> bookList = page.getContent();
            Pagination pagination = new Pagination(pageNumber, (int) page.getTotalElements());
            return new ApiDataResponse<Book>(bookList, pagination);
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
            book.setIsAvailable(false);
            bookRepository.save(book);
      }

      public void markAsReturned(Book book) {
            book.setIsAvailable(true);
            bookRepository.save(book);
      }

      public <T> String stringfy(T object) {
            try {
            return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                  throw new RuntimeException(e);
            }
      }
}