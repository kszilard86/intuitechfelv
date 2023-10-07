package com.example.feladat.validator;


import com.example.feladat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ClientEmailValidator implements Validator {

    private ClientRepository clientRepository;

    @Autowired
    public ClientEmailValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String clientEmail = (String) target;
        String emailRegex = "^(?=.{1,256}$)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        if (!(emailPattern.matcher(clientEmail).matches())) {
            errors.rejectValue("email", "email.not.valid");
        }
        if((clientRepository.findByEmail(clientEmail).isPresent())){
            errors.rejectValue("email", "email.exist");
        }

    }
}
