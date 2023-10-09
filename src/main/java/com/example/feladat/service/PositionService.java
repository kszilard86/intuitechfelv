package com.example.feladat.service;


import com.example.feladat.domain.Position;
import com.example.feladat.dto.incoming.PositionCommand;
import com.example.feladat.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Position> searchPositions(String keyword, String location) {

        List<Position> positionList = new ArrayList<>();

        positionList = positionRepository.searchPositions(keyword, location);

        return positionList;
    }
}
