package com.project.pma.validators;

import com.project.pma.Entities.Employee;
import com.project.pma.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        System.out.println("Entered validation method..");

        Employee emp=empRepo.findByEmail(value);

        if(emp!=null)
            return false;
        else
            return true;

    }
}
