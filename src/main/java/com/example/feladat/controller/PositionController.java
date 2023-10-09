package com.example.feladat.controller;

import com.example.feladat.domain.Position;
import com.example.feladat.dto.incoming.PositionCommand;
import com.example.feladat.service.ClientService;
import com.example.feladat.service.PositionService;
import com.example.feladat.validator.LocationValidator;
import com.example.feladat.validator.PositionNameValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    private final ClientService clientService;

    private final PositionNameValidator positionNameValidator;
    private final LocationValidator locationValidator;

    @InitBinder("name")
    public void initBinderPositionName(WebDataBinder binder) {
        binder.addValidators(positionNameValidator);
    }

    @InitBinder("location")
    public void initBinderLocation(WebDataBinder binder) {
        binder.addValidators(locationValidator);
    }

    @Autowired
    public PositionController(PositionService positionService, ClientService clientService, PositionNameValidator positionNameValidator, LocationValidator locationValidator) {
        this.positionService = positionService;
        this.clientService = clientService;
        this.positionNameValidator = positionNameValidator;
        this.locationValidator = locationValidator;
    }

    @PostMapping
    public ResponseEntity<String> createPosition(
            @RequestHeader("apiKey") String apiKey,
            @RequestParam @Valid String name,
            @RequestParam @Valid String location) {

        if (isValidApiKey(apiKey)) {

            PositionCommand positionCommand = new PositionCommand();
            positionCommand.setName(name);
            positionCommand.setLocation(location);

            Position position = positionService.createPosition(positionCommand);

            return ResponseEntity.status(HttpStatus.CREATED).body("/positions/" + position.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid api key!");
        }

    }

    private boolean isValidApiKey(String apiKey) {
        return clientService.findApiKey(apiKey);
    }

    @GetMapping
    public ResponseEntity<List<Position>> searchPositions(
            @RequestHeader("apiKey") String apiKey,
            @RequestParam @Valid String keyword,
            @RequestParam @Valid String location) {
        if (isValidApiKey(apiKey)) {

            List<Position> positionList = positionService.searchPositions(keyword, location);

            return ResponseEntity.status(HttpStatus.OK).body(positionList);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body((List<Position>) Collections.singletonMap("error", "Invalid API key"));
        }

    }
}
