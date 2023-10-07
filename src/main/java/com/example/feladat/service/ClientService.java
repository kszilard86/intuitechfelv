package com.example.feladat.service;

import com.example.feladat.domain.Client;
import com.example.feladat.dto.incoming.ClientCommand;
import com.example.feladat.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(ClientCommand clientCommand) {

        Client client = new Client(clientCommand);

        Client savedClient = clientRepository.save(client);

        return savedClient;
    }
}
