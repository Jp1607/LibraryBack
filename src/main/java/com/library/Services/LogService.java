package com.library.Services;

import com.library.Model.Enums.Activity;
import com.library.Model.Entities.Book;
import com.library.Model.Entities.Log;
import com.library.Model.Entities.Student;
import com.library.Model.Entities.User;
import com.library.Model.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    LogRepository logRepository;
    DateService dateService;
    UserService userService;

    @Autowired
    public LogService(LogRepository logRepository, DateService dateService, UserService userService) {
        this.logRepository = logRepository;
        this.dateService = dateService;
        this.userService = userService;
    }

    public void newLog(String tableName, Long id, Book book, Student student, Activity activity) {
        User u = userService.getUserByCred("admin", "123456");
        LocalDateTime date = dateService.getCurrentDate();
        Log log = new Log(u, student, book, date, activity.toString(), tableName, id);
        logRepository.save(log);
    }
}
