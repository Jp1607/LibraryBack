package com.example.demo.Repositories;

import com.example.demo.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public @Repository interface StudentRepository extends JpaRepository<Student, Long> {}
