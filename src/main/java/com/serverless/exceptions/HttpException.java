package com.serverless.exceptions;

public abstract class HttpException extends Exception
{
    private int httpStatusCode;
    private Object entity;

    public HttpException(int httpStatusCode)
    {
        super();
        this.httpStatusCode=httpStatusCode;
    }

    public HttpException(Object entity,int httpStatusCode)
    {
        super();
        this.httpStatusCode=httpStatusCode;
        this.entity=entity;
    }

    public HttpException(Object entity,int httpStatusCode,final String message,final Throwable cause)
    {
        super(message);
        initCause(cause);
        this.entity=entity;
        this.httpStatusCode=httpStatusCode;
    }

    public HttpException(int httpStatusCode,final String message,final Throwable cause)
    {
        super(message);
        initCause(cause);
        this.httpStatusCode=httpStatusCode;
    }
}
