package com.library.Services;

import com.library.Model.Entities.Student;
import com.library.Model.Enums.Activity;
import com.library.Model.Repositories.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService extends PersonService<Student>{

    StudentRepository studentRepository;
    ObjectMapper objectMapper;
    LogService logService;
    String tableName = "students";

    @Autowired
    public StudentService(StudentRepository studentRepository, ObjectMapper objectMapper, LogService logService){
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
        this.logService = logService;
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    protected List<Student> getAll() {
        return studentRepository.findAll();
    }

    public String stringfiedList() throws JsonProcessingException {
        return objectMapper.writeValueAsString(getAll());
    }

    public String stringfiedStudent(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(getById(id));
    }

    public void save(Student student) {
        studentRepository.save(student);
        logService.newLog(tableName, student.getId(), null, student, Activity.NEW);
    }

    public void edit(Student student) {
        studentRepository.save(student);
        logService.newLog(tableName, student.getId(), null, student, Activity.EDIT);
    }

    public void changeState(Long id) {
        Student student = getById(id);
        student.setActive(!student.getActive());
        logService.newLog(tableName, student.getId(), null, student, Activity.STATE);
        studentRepository.save(student);
    }

    public void exclude(Long id) {
        Student student = getById(id);
        student.setActive(false);
        logService.newLog(tableName, student.getId(), null, student, Activity.REMOVE);
        studentRepository.save(student);
    }
}
