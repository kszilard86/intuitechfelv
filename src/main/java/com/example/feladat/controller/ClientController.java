package com.example.feladat.controller;

import com.example.feladat.domain.Client;
import com.example.feladat.dto.incoming.ClientCommand;
import com.example.feladat.service.ClientService;
import com.example.feladat.validator.ClientEmailValidator;
import com.example.feladat.validator.ClientNameValidator;
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

    private final ClientNameValidator clientNameValidator;

    private final ClientEmailValidator clientEmailValidator;

    @Autowired
    public ClientController(ClientService clientService, ClientNameValidator clientNameValidator, ClientEmailValidator clientEmailValidator) {
        this.clientService = clientService;
        this.clientNameValidator = clientNameValidator;
        this.clientEmailValidator = clientEmailValidator;
    }

    @InitBinder("clientName")
    public void initBinderClientName(WebDataBinder binder) {
        binder.addValidators(clientNameValidator);
    }

    @InitBinder("clientEmail")
    public void initBinderClientEmail(WebDataBinder binder) {
        binder.addValidators(clientEmailValidator);
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestParam @Valid String clientName, @RequestParam @Valid String clientEmail) throws NoSuchAlgorithmException {

        ClientCommand clientCommand = new ClientCommand();
        clientCommand.setName(clientName);
        clientCommand.setEmail(clientEmail);

        Client client = clientService.saveClient(clientCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(client.getId());
    }



}
