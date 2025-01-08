package com.library.Services;

import com.library.Model.Entities.Parent;
import com.library.Model.Enums.Activity;
import com.library.Model.Repositories.ParentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParentService extends PersonService<Parent> {

    ParentRepository parentRepository;
    ObjectMapper objectMapper;
    LogService logService;
    String tableName = "";

    @Autowired
    public ParentService(ParentRepository parentRepository, ObjectMapper objectMapper, LogService logService) {
        this.logService = logService;
        this.objectMapper = objectMapper;
        this.parentRepository = parentRepository;
    }

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
        logService.newLog(tableName, parent.getId(), null, null, Activity.NEW);
        parentRepository.save(parent);
    }

    public void edit(Parent parent) {
        logService.newLog(tableName, parent.getId(), null, null, Activity.EDIT);
        parentRepository.save(parent);
    }

    public void changeState(Long id) {
        Parent parent = getById(id);
        parent.setActive(!parent.getActive());
        logService.newLog(tableName, id, null, null, Activity.STATE);
        parentRepository.save(parent);
    }

    public void exclude(Long id) {
        Parent parent = getById(id);
        parent.setActive(false);
        logService.newLog(tableName, id, null, null, Activity.REMOVE);
        parentRepository.save(parent);
    }
}

