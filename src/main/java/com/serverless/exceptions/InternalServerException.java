package com.serverless.exceptions;

import org.apache.http.HttpStatus;

public class InternalServerException extends HttpException
{
    public InternalServerException()
    {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(Object entity)
    {
        super(entity,HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(final String message,final Throwable cause)
    {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR,message,cause);
    }

    public InternalServerException(Object entity,final String message,Throwable cause)
    {
        super(entity,HttpStatus.SC_INTERNAL_SERVER_ERROR,message,cause);
    }
}
