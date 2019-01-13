package com.serverless.response;

import com.serverless.models.ConstraintViolationDescription;
import java.util.List;

public class ConstraintViolationResponseError
{
    private String message;
    private List<ConstraintViolationDescription> errors;

    public void setMessage(String message)
    {
        this.message=message;
    }
    public String getMessage()
    {
        return message;
    }
    public void setErrors(List<ConstraintViolationDescription> errors)
    {
        this.errors=errors;
    }
    public List<ConstraintViolationDescription> getErrors()
    {
        return errors;
    }
}
