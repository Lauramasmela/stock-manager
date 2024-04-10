package com.lsof.stockmanager;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();



    public void validate(T objectValidate){
        Set<ConstraintViolation<T>> violations =  validator.validate(objectValidate);

        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

        }

    }
}
