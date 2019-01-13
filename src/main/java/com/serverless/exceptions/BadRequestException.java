package com.serverless.exceptions;

import org.apache.http.HttpStatus;

import java.io.Serializable;

public class BadRequestException extends HttpException
{

    public BadRequestException() {
        super(HttpStatus.SC_BAD_REQUEST);
    }

    public BadRequestException(Object entity) {
        super(entity,HttpStatus.SC_BAD_REQUEST);
    }

    public BadRequestException(String message, Throwable cause) {
        super(HttpStatus.SC_BAD_REQUEST,message, cause);
    }

    public BadRequestException(Object entity,String message, Throwable cause) {
        super(entity,HttpStatus.SC_BAD_REQUEST ,message, cause);
    }
}
