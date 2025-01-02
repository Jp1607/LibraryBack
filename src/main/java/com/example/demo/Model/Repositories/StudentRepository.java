package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public @Repository interface StudentRepository extends JpaRepository<Student, Long> {}
