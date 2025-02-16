package com.library.Services;
import com.library.Model.Enums.Activity;
import com.library.Model.Entities.Book;
import com.library.Model.Entities.Log;
import com.library.Model.Entities.Student;
import com.library.Model.Entities.User;
import com.library.Model.Repositories.LogRepository;
import com.library.Model.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LogService {

    LogRepository logRepository;
    DateService dateService;
    UserRepository userRepository;

    @Autowired
    public LogService(LogRepository logRepository, DateService dateService, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.dateService = dateService;
        this.userRepository = userRepository;
    }

    public void newLog(String tableName, Long id, Book book, Student student, Activity activity){
        User u = new User();
        u.setName("admin");
        u.setPassword("123456");
        userRepository.save(u);
        LocalDateTime date = dateService.getCurrentDate();
        Log log = new Log(u, student, book, date, activity, tableName, id);
        logRepository.save(log);
    }
}
