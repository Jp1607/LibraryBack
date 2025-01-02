package com.example.demo.Services;
import com.example.demo.Model.Enums.Activity;
import com.example.demo.Model.Entities.Book;
import com.example.demo.Model.Entities.User;
import com.example.demo.Model.Repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    DateService dateService;
    LogService logService;
    ObjectMapper objectMapper;
    BookRepository bookRepository;

    public String getBookByTitle(String title) {
        try {
        List<Book> bookList = bookRepository.findByTitle(title);
        return objectMapper.writeValueAsString(bookList);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Should have error handling
    public Book getBookByPatrimonialId(Long id) {
        return bookRepository.findByPatrimonialId(id);
    }


    public void addBook(Book book) {
        bookRepository.save(book);
        User user = new User();
        logService.newLog(user, book, null, dateService.getCurrentDate(), Activity.NEW);
    }

    public void editBook(Book book) {
        bookRepository.save(book);
        User user = new User();
        logService.newLog(user, book, null, dateService.getCurrentDate(), Activity.EDIT);
    }

    public void excludeBook(Long id) {
        Book tempBook = bookRepository.findByPatrimonialId(id);
        tempBook.setExcluded(true);
        bookRepository.save(tempBook);
        User user = new User();
        logService.newLog(user, tempBook, null, dateService.getCurrentDate(), Activity.REMOVE);
    }

    public void markAsBorrowed(Book book){
        book.setAvailable(false);
        bookRepository.save(book);
    }

    public void markAsReturned(Book book){
        book.setAvailable(true);
        bookRepository.save(book);
    }
}
