package com.example.feladat.service;

import com.example.feladat.domain.Client;
import com.example.feladat.domain.Position;
import com.example.feladat.dto.incoming.PositionCommand;
import com.example.feladat.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PositionService {

    private final PositionRepository positionRepository;


    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(PositionCommand positionCommand) {
        Position position = new Position(positionCommand);

        positionRepository.save(position);

        return position;
    }
}
