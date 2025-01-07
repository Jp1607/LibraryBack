package com.library.Services;

import com.library.Model.Entities.Parent;
import com.library.Model.Repositories.ParentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParentService extends PersonService<Parent> {

    @Autowired
    ParentRepository parentRepository;
    ObjectMapper objectMapper;

    @Override
    protected Parent getById(Long id) {
        return parentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    protected List<Parent> getAll() {
        return parentRepository.findAll();
    }

    public String stringfiedList() throws JsonProcessingException {
        return objectMapper.writeValueAsString(getAll());
    }

    public String stringfiedParent(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(getById(id));
    }

    public void save(Parent parent) {
        parentRepository.save(parent);
    }

    public void edit(Parent parent) {
        parentRepository.save(parent);
    }

    public void changeState(Long id) {
        Parent parent = getById(id);
        parent.setActive(!parent.getActive());
        parentRepository.save(parent);
    }

    public void exclude(Long id) {
        Parent parent = getById(id);
        parent.setActive(false);
        parentRepository.save(parent);
    }
}

