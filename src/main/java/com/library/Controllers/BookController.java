package com.library.Controllers;

import com.library.Model.DTO.BookDTO;
import com.library.Services.BookService;
import com.library.Model.Entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/books")
public class BookController {

      HttpStatus httpStatus;

      @Autowired
      BookService bookService;

      @GetMapping(value = "", produces = "application/json")
      public ResponseEntity<String> getBooks(
              @RequestParam(value = "title", required = false) String title,
              @RequestParam(value = "id", required = false) Long id,
              @RequestParam(value = "page", defaultValue = "0", required = false) int page
      ) {
            try {
                  String booksList;
                  if (title != null) {
                        booksList = bookService.getBookByTitle(title, page);
                  } else if (id != null) {
                        booksList = bookService.getStringfiedBookById(id);
                  } else {
                        booksList = bookService.getAll(page);
                  }
                  httpStatus = HttpStatus.OK;
                  return ResponseEntity.status(httpStatus.value()).body(booksList);
            } catch (Exception e) {
                  httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                  return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
            }
      }

      @PostMapping(value = "", produces = "text/plain")
      public ResponseEntity<String> newBook(@RequestBody BookDTO bookDTO) {

            try {
                  bookService.addBook(bookDTO);
                  httpStatus = HttpStatus.OK;
                  return ResponseEntity.status(httpStatus.value()).body("Livro salvo com sucesso!");
            } catch (Exception e) {
                  httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                  return ResponseEntity.status(httpStatus.value()).body(e.getMessage());
            }
      }

      @PutMapping(value = "/edit", produces = "text/plain")
      public ResponseEntity<String> editBook(@RequestBody Book book) {
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
      public ResponseEntity<String> excludeBook(@RequestParam(value = "id") Long id) {
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
