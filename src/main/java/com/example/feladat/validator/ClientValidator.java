package com.example.feladat.validator;

import com.example.feladat.dto.incoming.ClientCommand;
import com.example.feladat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ClientValidator implements Validator {


    private ClientRepository clientRepository;

    @Autowired
    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return ClientCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ClientCommand clientCommand = (ClientCommand) target;
        String emailRegex = "^(?=.{1,256}$)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        if (clientCommand.getName() == null){
            errors.rejectValue("name", "name.required");
        }
        if (clientCommand.getName().length() < 3){
            errors.rejectValue("name", "name.too.short");
        }
        if (clientCommand.getName().length() > 100){
            errors.rejectValue("name", "name.too.long");
        }
        if (!(emailPattern.matcher(clientCommand.getEmail()).matches())) {
            errors.rejectValue("email", "email.not.valid");
        }
        if((clientRepository.findByEmail(clientCommand.getEmail()).isPresent())){
            errors.rejectValue("email", "email.exist");
        }
    }
}
