package com.library.Model.Entities;
import com.library.Model.Enums.Activity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "activity")
    private String activity;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_id")
    private Long tableId;

    public Log(User user, Student student, Book book, LocalDateTime date, String activity, String tableName, Long tableId) {
        this.user = user;
        this.student = student;
        this.book = book;
        this.date = date;
        this.activity = activity;
        this.tableName = tableName;
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity.getDescription();
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", user=" + user +
                ", student=" + student +
                ", book=" + book +
                ", date=" + date +
                ", activity='" + activity + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableId=" + tableId +
                '}';
    }
}
