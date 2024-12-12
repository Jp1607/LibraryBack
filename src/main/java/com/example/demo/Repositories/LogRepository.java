package com.example.demo.Repositories;

import com.example.demo.Entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
