package com.example.feladat.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
