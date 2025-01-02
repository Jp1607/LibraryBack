package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
