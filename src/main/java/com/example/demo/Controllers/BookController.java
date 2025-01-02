package com.example.demo.Controllers;
import com.example.demo.Services.BookService;
import com.example.demo.Model.Entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    HttpStatus httpStatus;

    @Autowired
    BookService bookService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<String> getBooks(@RequestParam(value = "title", required = false) String title) {
        try {
            String booksList = bookService.getBookByTitle(title);
            httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body(booksList);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }

    @PostMapping(value = "", produces = "text/plain")
    public ResponseEntity<String> newBook(@RequestBody() Book book) {
        try {
            bookService.addBook(book);
            httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body("Livro salvo com sucesso!");
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }

    @PutMapping(value = "/edit", produces = "text/plain")
    public ResponseEntity<String> editBook(@RequestBody() Book book) {
        try {
            bookService.editBook(book);
            httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body("Livro editado com sucesso!");
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }

    @PutMapping(value = "/exclude", produces = "text/plain")
    public ResponseEntity<String> excludeBook(@RequestParam(value = "patrimonial_id") Long id) {
        try {
            bookService.excludeBook(id);
            httpStatus = HttpStatus.OK;
            return ResponseEntity.status(httpStatus.value()).body("livro excluído com sucesso!");
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
        }
    }
}
