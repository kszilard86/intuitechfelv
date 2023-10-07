package com.example.feladat.validator;

import com.example.feladat.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PositionNameValidator implements Validator {

    private PositionRepository positionRepository;

    @Autowired
    public PositionNameValidator(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String positionName = (String) target;

        if (positionName.length() > 50){
            errors.rejectValue("name", "position.name.too.long");
        }
    }
}
