package com.library.Services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class PersonService<T> {

    protected abstract T getById(Long id);
    protected abstract List<T> getAll();

    protected Object getPerson(Long id) {
        if (id == null) {
            return getAll();
        } else {
            return getById(id);
        }
    }
}
