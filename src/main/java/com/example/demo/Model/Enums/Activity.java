package com.example.demo.Model.Enums;

public enum Activity {
    NEW("adicionou"),
    REMOVE("removeu"),
    EDIT("edtou"),
    BORROW("emprestou"),
    RETURN("devolveu");

    private final String description;

    Activity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
