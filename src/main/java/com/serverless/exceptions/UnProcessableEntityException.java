package com.serverless.exceptions;

import org.apache.http.HttpStatus;

public class UnProcessableEntityException extends HttpException
{
    public UnProcessableEntityException()
    {
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    public UnProcessableEntityException(Object entity)
    {
        super(entity,HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    public UnProcessableEntityException(final String message,final Throwable cause)
    {
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY,message,cause);
    }

    public UnProcessableEntityException(Object entity,final String message,Throwable cause)
    {
        super(entity,HttpStatus.SC_UNPROCESSABLE_ENTITY,message,cause);
    }
}
