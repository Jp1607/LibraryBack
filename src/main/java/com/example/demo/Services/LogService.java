package com.example.demo.Services;
import com.example.demo.Model.Enums.Activity;
import com.example.demo.Model.Entities.Book;
import com.example.demo.Model.Entities.Log;
import com.example.demo.Model.Entities.Student;
import com.example.demo.Model.Entities.User;
import com.example.demo.Model.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    public void newLog(User user, Book book, Student student, LocalDateTime date, Activity activity){
        Log log = new Log(user, student, book, date, activity);
        logRepository.save(log);
    }
}
