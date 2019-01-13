package com.serverless.base;

import com.serverless.base.json.JsonRequestBodyDeserializerStrategy;
import org.apache.http.entity.ContentType;

import java.util.Optional;
import java.util.stream.Stream;

public enum DeserializerStrategy
{

    APPLICATION_JSON(ContentType.APPLICATION_JSON.getMimeType(),new JsonRequestBodyDeserializerStrategy());
    private String contentType;
    private RequestBodyDeserializerStrategy deserializerStrategy;

    DeserializerStrategy(String contentType,RequestBodyDeserializerStrategy deserializerStrategy)
    {
        this.contentType=contentType;
        this.deserializerStrategy=deserializerStrategy;
    }

    public String getContentType()
    {
        return contentType;
    }

    public RequestBodyDeserializerStrategy getDeserializerStrategy()
    {
        return deserializerStrategy;
    }

    public static Optional<DeserializerStrategy> strategy(String contentType)
    {
        return Stream.of(DeserializerStrategy.values()).
                filter(e->e.getContentType().equals(contentType)).findFirst();
    }
}
