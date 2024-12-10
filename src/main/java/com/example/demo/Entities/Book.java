package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table (name = "book")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "patrimonial_id")
    private Integer patrimId;

    @Column(name = "shelf")
    private Integer shelf;

    @Column(name = "is_available")
    private Boolean isAvailable;
}
