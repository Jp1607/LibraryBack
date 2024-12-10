package com.example.demo.Services;

import com.example.demo.Entities.Book;
import com.example.demo.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getBookByTitle(String title){
       return bookRepository.findByTitle(title);
    }

    public Book editBook(Book book){
        return bookRepository.save(book);
    }

    public  void addBook(Book book){
        bookRepository.save(book);
    }
}
