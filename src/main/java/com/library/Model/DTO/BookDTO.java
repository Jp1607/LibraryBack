package com.library.Model.DTO;

public class BookDTO {
    private String title;
    private String author;
    private String genre;
    private Integer patrimonialId;
    private Integer shelf;

    public BookDTO(String title, String author, String genre, Integer patrimonialId, Integer shelf) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.patrimonialId = patrimonialId;
        this.shelf = shelf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPatrimonialId() {
        return patrimonialId;
    }

    public void setPatrimonialId(Integer patrimonialId) {
        this.patrimonialId = patrimonialId;
    }

    public Integer getShelf() {
        return shelf;
    }

    public void setShelf(Integer shelf) {
        this.shelf = shelf;
    }
}
