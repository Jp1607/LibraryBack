package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
