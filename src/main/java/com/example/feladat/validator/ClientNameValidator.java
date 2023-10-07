package com.example.feladat.validator;

import com.example.feladat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ClientNameValidator implements Validator {


    private ClientRepository clientRepository;

    @Autowired
    public ClientNameValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String clientName = (String) target;


        if (clientName == null){
            errors.rejectValue("clientName", "name.required");
        }
        if (clientName.length() < 3){
            errors.rejectValue("clientName", "name.too.short");
        }
        if (clientName.length() > 100){
            errors.rejectValue("clientName", "name.too.long");
        }

    }
}
