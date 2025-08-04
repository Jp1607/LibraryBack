package com.library.Services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class PersonService<T> {

    protected abstract T getById(Long id);
    protected abstract Page<T> getAll(int page);

    protected Object getPerson(Long id, int page) {
        if (id == null) {
            return getAll(page);
        } else {
            return getById(id);
        }
    }
}
