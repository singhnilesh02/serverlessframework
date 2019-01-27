package com.serverless.exceptions;

import org.apache.http.HttpStatus;

public class TrackingNumberException extends HttpException
{
    public TrackingNumberException()
    {
        super(HttpStatus.SC_NOT_IMPLEMENTED);
    }

    public TrackingNumberException(Object entity)
    {
        super(entity,HttpStatus.SC_NOT_IMPLEMENTED);
    }

    public TrackingNumberException(final String message,Throwable cause)
    {
        super(HttpStatus.SC_NOT_IMPLEMENTED,message,cause);
    }

    public TrackingNumberException(Object entity,final String message,Throwable cause)
    {
        super(entity,HttpStatus.SC_NOT_IMPLEMENTED,message,cause);
    }
}
