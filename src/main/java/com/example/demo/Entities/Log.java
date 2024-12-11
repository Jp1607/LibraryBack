package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;



}
