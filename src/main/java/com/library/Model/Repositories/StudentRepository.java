package com.library.Model.Repositories;

import com.library.Model.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public @Repository interface StudentRepository extends JpaRepository<Student, Long> {}
