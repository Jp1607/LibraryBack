package com.example.demo.Services;

import com.example.demo.Model.Entities.Parent;
import com.example.demo.Model.Repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParentService extends PersonService<Parent>{

    @Autowired
    ParentRepository parentRepository;

    @Override
    protected Parent getById(Long id) {
        return parentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    protected List<Parent> getAll() {
        return parentRepository.findAll();
    }
}
