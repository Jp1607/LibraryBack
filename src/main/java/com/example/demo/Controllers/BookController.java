package com.example.demo.Controllers;
import com.example.demo.Services.BookService;
import com.example.demo.Entities.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    HttpStatus httpStatus;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookService bookService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<String> getBooks(@RequestParam(value = "title", required = false) String title) {
        try {
            List<Book> booksList = bookService.getBookByTitle(title);
            String bookListAsString = objectMapper.writeValueAsString(booksList);
            httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body(bookListAsString);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }

    @PostMapping(value = "", produces = "text/plain")
    public ResponseEntity<String> newBook(@RequestBody(required = true) Book book) {
        try {
            bookService.addBook(book);
            httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body("Livro salvo com sucesso!");
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }

    @PutMapping(value = "", produces = "text/plain")
public ResponseEntity<String> editBook(@RequestBody(required = true) Book book){
        try {
     bookService.editBook(book);
     httpStatus = HttpStatus.OK;
     return ResponseEntity.status(httpStatus.value()).body("Livro editado com sucesso!");
        } catch (Exception e) {
        httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }
}
