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

    @Column(name = "is_excluded")
    private Boolean isExcluded;

    public Book(Long id, String title, String author, String genre, Integer patrimId, Integer shelf, Boolean isAvailable, Boolean isExcluded) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.patrimId = patrimId;
        this.shelf = shelf;
        this.isAvailable = isAvailable;
        this.isExcluded = isExcluded;
    }


    public Boolean getExcluded() {
        return isExcluded;
    }

    public void setExcluded(Boolean excluded) {
        isExcluded = excluded;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getShelf() {
        return shelf;
    }

    public void setShelf(Integer shelf) {
        this.shelf = shelf;
    }

    public Integer getPatrimId() {
        return patrimId;
    }

    public void setPatrimId(Integer patrimId) {
        this.patrimId = patrimId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }
}
