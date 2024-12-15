package com.example.demo.Controllers;

import com.example.demo.Entities.BorrowedBook;
import com.example.demo.Repositories.BorrowedBookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Controller da entrada/empréstimo
//e saída/devolução de livros.
@RestController
@RequestMapping(value = "flow")
public class BookFlowController {

    HttpStatus httpStatus = HttpStatus.OK;
    String body;

    @Autowired
    BorrowedBookRepository borrowedBookRepository;

    //Retorna uma lista do histórico de movimentação
    //Will accept sorting in future
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<String> getBookFlowList() {
        try {
            List<BorrowedBook> borrowedBookList = borrowedBookRepository.findAll();
            ObjectMapper objectMapper = new ObjectMapper();
            body = objectMapper.writeValueAsString(borrowedBookList);
            return ResponseEntity.status(httpStatus).body(body);
        } catch (Exception e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = e.getMessage();
            return ResponseEntity.status(httpStatus).body(body);
        }
    }

    @PostMapping
}
