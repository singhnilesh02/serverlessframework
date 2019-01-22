package com.serverless.response;

public class DefaultErrorResponse
{
    private String message;

    public DefaultErrorResponse(String message)
    {
        this.message=message;
    }

    public void setMessage(String message)
    {
        this.message=message;
    }

    public String getMessage()
    {
        return this.message;
    }
}
