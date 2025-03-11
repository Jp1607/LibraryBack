package com.library.Model.Repositories;

import com.library.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
User findByNameAndPassword(String name, String password);
}
