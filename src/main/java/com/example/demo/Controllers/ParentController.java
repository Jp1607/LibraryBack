package com.example.demo.Controllers;

import com.example.demo.Model.Entities.Parent;
import com.example.demo.Services.ParentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    ParentService parentService;

    HttpStatus httpStatus = HttpStatus.OK;
    String body;

    @GetMapping("")
    public ResponseEntity<String> getParents(@RequestParam(required = false) Long id) {

        try {
            if (id == null) {
                return ResponseEntity.status(httpStatus.value()).body(parentService.stringfiedList());
            } else {
                return ResponseEntity.status(httpStatus.value()).body(parentService.stringfiedParent(id));
            }
        } catch (JsonProcessingException e) {
            body = "Erro ao gerar JSON: ";
            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
        } catch (Exception e) {
            {
                body = "Erro ao buscar pais: ";
                return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
            }
        }
    }

    @PostMapping("")
    public ResponseEntity<String> newParent(@RequestBody Parent parent) {

        parentService.save(parent);
        return ResponseEntity.status(httpStatus.value()).build();
    }

    @PutMapping("edit")
    public ResponseEntity<String> editParent(@RequestBody Parent parent) {

        parentService.edit(parent);
        return ResponseEntity.status(httpStatus.value()).build();
    }

    @PutMapping("state")
    public ResponseEntity<String> changeStateParent(@RequestParam Long id) {

        parentService.changeState(id);
        return ResponseEntity.status(httpStatus.value()).build();
    }

    @PutMapping("exclude")
    public ResponseEntity<String> excludeParent(@RequestParam Long id) {

        parentService.exclude(id);
        return ResponseEntity.status(httpStatus.value()).build();
    }
}
