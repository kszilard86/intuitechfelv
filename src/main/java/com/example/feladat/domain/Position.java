package com.example.feladat.domain;


import com.example.feladat.dto.incoming.PositionCommand;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String location;


    public Position(PositionCommand positionCommand) {

        this.name = positionCommand.getName();
        this.location = positionCommand.getLocation();

    }


}
