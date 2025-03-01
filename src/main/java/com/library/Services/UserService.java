package com.library.Services;

import com.library.Model.Entities.User;
import com.library.Model.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getuserList(){
    return userRepository.findAll();
}

public User getUser(Long id){
    return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
}
}
