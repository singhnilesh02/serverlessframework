package com.serverless.base;

public interface RequestBodyDeserializerStrategy
{
    <T> T deserialize(String body,Class<?> entityClass) throws Exception;
}
