package com.library.Controllers;

import com.library.Model.Entities.Student;
import com.library.Model.Enums.Activity;
import com.library.Services.LogService;
import com.library.Services.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    LogService logService;

    HttpStatus httpStatus = HttpStatus.OK;
    String body;
    String tableName = "students";

    @GetMapping("")
    public ResponseEntity<String> getstudents(@RequestParam(required = false) Long id) {

        try {
            if (id == null) {
                return ResponseEntity.status(httpStatus.value()).body(studentService.stringfiedList());
            } else {
                return ResponseEntity.status(httpStatus.value()).body(studentService.stringfiedStudent(id));
            }
        } catch (JsonProcessingException e) {
            body = "Erro ao gerar JSON: ";
            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
        } catch (Exception e) {
            {
                body = "Erro ao buscar alunos: ";
                return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
            }
        }
    }

    @PostMapping("")
    public ResponseEntity<String> newStudent(@RequestBody Student student) {

        try {
            Student s = studentService.save(student);
            logService.newLog(tableName, s.getId(), null, s, Activity.NEW);
            return ResponseEntity.status(httpStatus.value()).build();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = "Erro ao salvar: ";
            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<String> editStudent(@RequestBody Student student) {

        try {
            studentService.edit(student);
            logService.newLog(tableName, student.getId(), null, student, Activity.EDIT);
            return ResponseEntity.status(httpStatus.value()).build();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = "Erro ao editar: ";
            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
        }

    }

    @PatchMapping("")
    public ResponseEntity<String> changeStateStudent(@RequestParam Long id) {

        try {
            Student s = studentService.getById(id);
            studentService.changeState(id);
            logService.newLog(tableName, s.getId(), null, s, Activity.STATE);
            return ResponseEntity.status(httpStatus.value()).build();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = "Erro ao editar estado: ";
            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
        }
    }

    @PatchMapping("/exclude")
    public ResponseEntity<String> excludeStudent(@RequestParam Long id) {

        try {
            Student s = studentService.getById(id);
            studentService.exclude(id);
            logService.newLog(tableName, s.getId(), null, s, Activity.REMOVE);
            return ResponseEntity.status(httpStatus.value()).build();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = "Erro ao excluir: ";
            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
        }
    }
}
