package com.serverless.models;

public class ConstraintViolationDescription
{
    private String attribute;
    private String message;

    public ConstraintViolationDescription(String attribute,String message)
    {
        this.attribute=attribute;
        this.message=message;
    }

    public void setMessage(String message)
    {
        this.message=message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setAttribute(String attribute)
    {
        this.attribute=attribute;
    }

    public String getAttribute()
    {
        return attribute;
    }
}
