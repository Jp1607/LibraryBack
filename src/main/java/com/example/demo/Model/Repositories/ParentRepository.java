package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
