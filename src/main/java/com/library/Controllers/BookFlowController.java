package com.library.Controllers;
import com.library.Services.BorrowedBookService;
import com.library.Services.DateService;
import com.library.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller da entrada/empréstimo
//e saída/devolução de livros.
@RestController
@CrossOrigin(origins = "*")
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
    public ResponseEntity<String> getBookFlowList(@RequestParam(value = "id", required = false) Long id,
    @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        try {
            if(id != null){
               body = borrowedBookService.stringBookList(borrowedBookService.getBorrowedBookById(id));
                return ResponseEntity.status(httpStatus).body(body);
            }
            body = borrowedBookService.stringBookList(borrowedBookService.getBorrowedBookList(page));
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
            body = borrowedBookService.borrowedBookAction(bookId, studentId);
            return ResponseEntity.status(httpStatus).body(body);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = e.getMessage();
            return ResponseEntity.status(httpStatus).body(body);
        }
    }
}
