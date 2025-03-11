package com.library.Model.Entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_flow")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    public BorrowedBook() {
    }

    public BorrowedBook(Book book, Student student, User user, LocalDateTime borrowDate) {
        this.book = book;
        this.student = student;
        this.user = user;
        this.borrowDate = borrowDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
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

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "id=" + id +
                ", book=" + book +
                ", student=" + student +
                ", user=" + user +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
