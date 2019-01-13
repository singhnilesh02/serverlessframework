package com.serverless.base;

public interface ResponseBodySerializerStrategy
{
    String serialize(Object entity) throws Exception;
}
