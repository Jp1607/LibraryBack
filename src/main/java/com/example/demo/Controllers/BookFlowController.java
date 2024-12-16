package com.example.demo.Controllers;

import com.example.demo.Entities.User;
import com.example.demo.Services.BorrowedBookService;
import com.example.demo.Services.DateService;
import com.example.demo.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller da entrada/empréstimo
//e saída/devolução de livros.
@RestController
@RequestMapping(value = "flow")
public class BookFlowController {

    HttpStatus httpStatus = HttpStatus.OK;
    String body;

    @Autowired
    BorrowedBookService borrowedBookService;
    StudentService studentService;
    DateService dateService;

    //Retorna uma lista do histórico de movimentação
    //Will accept sorting in future
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<String> getBookFlowList() {
        try {
            body = borrowedBookService.getStringfiedBookList();
            return ResponseEntity.status(httpStatus).body(body);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = e.getMessage();
            return ResponseEntity.status(httpStatus).body(body);
        }
    }

    @PostMapping(produces = "text/plain")
    public ResponseEntity<String> bookFlowAction(@RequestParam(value = "bookId") Long bookId,
                                                 @RequestParam(value = "studentId") Long studentId) {
        try {
            User u = new User();
            borrowedBookService.newBorrowedBook(bookId, studentService.getStudentById(studentId), u, dateService.getCurrentDate(), false);
            body = "Livro emprestado com sucesso!";
            return ResponseEntity.status(httpStatus).body(body);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = e.getMessage();
            return ResponseEntity.status(httpStatus).body(body);
        }
    }
}
