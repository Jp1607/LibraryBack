package com.library.Controllers;

import com.library.Model.Entities.Parent;
import com.library.Services.LogService;
import com.library.Services.ParentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/parent")
public class ParentController {
//
//    ParentService parentService;
//    HttpStatus httpStatus = HttpStatus.OK;
//    String body;
//
//    @Autowired
//    public ParentController(ParentService parentService){
//        this.parentService = parentService;
//    }
//
//    @GetMapping("")
//    public ResponseEntity<String> getParents(@RequestParam(required = false) Long id) {
//
//        try {
//            if (id == null) {
//                return ResponseEntity.status(httpStatus.value()).body(parentService.stringfiedList());
//            } else {
//                return ResponseEntity.status(httpStatus.value()).body(parentService.stringfiedParent(id));
//            }
//        } catch (JsonProcessingException e) {
//            body = "Erro ao gerar JSON: ";
//            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
//        } catch (Exception e) {
//            {
//                body = "Erro ao buscar pais: ";
//                return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
//            }
//        }
//    }
//
//    @PostMapping("")
//    public ResponseEntity<String> newParent(@RequestBody Parent parent) {
//
//        try {
//            parentService.save(parent);
//            return ResponseEntity.status(httpStatus.value()).build();
//        } catch (Exception e) {
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//            body = "Erro ao salvar: ";
//            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
//        }
//    }
//
//    @PutMapping("")
//    public ResponseEntity<String> editParent(@RequestBody Parent parent) {
//
//        try {
//            parentService.edit(parent);
//            return ResponseEntity.status(httpStatus.value()).build();
//        } catch (Exception e) {
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//            body = "Erro ao editar: ";
//            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
//        }
//
//    }
//
//    @PatchMapping("")
//    public ResponseEntity<String> changeStateParent(@RequestParam Long id) {
//
//        try {
//            parentService.changeState(id);
//            return ResponseEntity.status(httpStatus.value()).build();
//        } catch (Exception e) {
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//            body = "Erro ao editar estado: ";
//            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
//        }
//    }
//
//    @PatchMapping("exclude")
//    public ResponseEntity<String> excludeParent(@RequestParam Long id) {
//
//        try {
//            parentService.exclude(id);
//            return ResponseEntity.status(httpStatus.value()).build();
//        } catch (Exception e) {
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//            body = "Erro ao excluir: ";
//            return ResponseEntity.status(httpStatus.value()).body(body + e.getMessage());
//        }
//    }
}
