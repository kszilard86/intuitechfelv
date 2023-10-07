package com.example.feladat.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionCommand {

    private String name;
    private String location;

}
