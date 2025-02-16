package com.library.Model.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "patrimonial_id")
    private Integer patrimonialId;

    @Column(name = "shelf")
    private Integer shelf;

    @Column(name = "is_available")
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @Column(name = "is_excluded")
    @JsonProperty("isExcluded")
    private boolean isExcluded;

    public Book() {
    }

    public Book(Long id, String title, String author, String genre, Integer patrimonialId, Integer shelf, Boolean isAvailable, Boolean isExcluded) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.patrimonialId = patrimonialId;
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

    public void setPatrimId(Integer patrimId) {
        this.patrimonialId = patrimId;
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

    public Integer getPatrimonialId() {
        return patrimonialId;
    }

    public void setPatrimonialId(Integer patrimonialId) {
        this.patrimonialId = patrimonialId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", patrimonialId=" + patrimonialId +
                ", shelf=" + shelf +
                ", isAvailable=" + isAvailable +
                ", isExcluded=" + isExcluded +
                '}';
    }
}
