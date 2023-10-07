package com.example.feladat.controller;

import com.example.feladat.domain.Client;
import com.example.feladat.dto.incoming.ClientCommand;
import com.example.feladat.service.ClientService;
import com.example.feladat.validator.ClientValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    private final ClientValidator clientValidator;


    @Autowired
    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @InitBinder("clientCommand")
    public void initBinderClientCommand(WebDataBinder binder) {
        binder.addValidators(clientValidator);
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody @Valid ClientCommand clientCommand) throws NoSuchAlgorithmException {
        Client client = clientService.saveClient(clientCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(client.getId());
    }



}
