package com.library.Model.Enums;

public enum BookFlowAction {
    BORROW("emprestou"),
    RETURN("devolveu");

    private final String description;

    BookFlowAction(String description) { this.description = description; }

    public String getDescription() { return description; }

}
