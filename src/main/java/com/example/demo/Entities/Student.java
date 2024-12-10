package com.example.demo.Entities;

import jakarta.persistence.Column;

public class Student extends Person{

    @Column(name = "class")
    private Integer classRoom;
}
