package com.library.Model.Enums;

public enum Activity {
    NEW("adicionou"),
    REMOVE("removeu"),
    EDIT("editou"),
    BORROW("emprestou"),
    STATE("editou estado"),
    RETURN("devolveu");

    private final String description;

    Activity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
