package com.serverless.validation;

import com.serverless.exceptions.HttpException;

public interface RequestValidator
{
    void validate(Object entity) throws HttpException;
}
