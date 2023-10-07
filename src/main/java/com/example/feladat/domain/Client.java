package com.example.feladat.domain;

import com.example.feladat.dto.incoming.ClientCommand;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private String email;


    public Client(ClientCommand clientCommand) {
        this.name = clientCommand.getName();
        this.email = clientCommand.getEmail();
    }

}
