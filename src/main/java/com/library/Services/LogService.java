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

    @Autowired
    LogRepository logRepository;
    DateService dateService;

    public void newLog(String tableName, Long id, Book book, Student student, Activity activity){
        User u = new User();
        LocalDateTime date = dateService.getCurrentDate();
        Log log = new Log(u, student, book, date, activity, tableName, id);
        logRepository.save(log);
    }
}
