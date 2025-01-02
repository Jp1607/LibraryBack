package com.example.demo.Services;

import com.example.demo.Model.Entities.Student;
import com.example.demo.Model.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService extends PersonService<Student>{

    @Autowired
    StudentRepository studentRepository;

    @Override
    protected Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    protected List<Student> getAll() {
        return studentRepository.findAll();
    }
}
