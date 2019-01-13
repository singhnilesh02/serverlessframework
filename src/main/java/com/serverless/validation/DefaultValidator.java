package com.serverless.validation;

import com.serverless.exceptions.HttpException;
import com.serverless.exceptions.UnProcessableEntityException;
import com.serverless.models.ConstraintViolationDescription;
import com.serverless.response.ConstraintViolationResponseError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class DefaultValidator implements RequestValidator
{
    public static final String UNPROCESSABLE_ENTITY_MESSAGE = "Unprocessable entity";

    @Override
    public void validate(Object entity) throws HttpException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations=validator.validate(entity);

        if(!violations.isEmpty())
        {
            List<ConstraintViolationDescription> errors=new ArrayList<>();
            ConstraintViolationResponseError error =new ConstraintViolationResponseError();
            error.setMessage(UNPROCESSABLE_ENTITY_MESSAGE);
            for(ConstraintViolation<Object> violation:violations)
            {
                String attribute=violation.getPropertyPath().toString();
                String message=violation.getMessage();
                errors.add(new ConstraintViolationDescription(attribute,message));
            }
            error.setErrors(errors);
            throw new UnProcessableEntityException(error);
        }
    }
}
