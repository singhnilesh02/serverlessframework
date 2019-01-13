package com.serverless.base;

import java.util.Optional;
import java.util.stream.Stream;

import com.serverless.base.json.JsonResponseBodySerializerStrategy;
import org.apache.http.entity.ContentType;

public enum SerializerStrategy
{
    APPLICATION_JSON(ContentType.APPLICATION_JSON.getMimeType(),new JsonResponseBodySerializerStrategy());

    private String contentType;
    private ResponseBodySerializerStrategy serializerStrategy;

    SerializerStrategy(String contentType,ResponseBodySerializerStrategy strategy)
    {
        this.contentType=contentType;
        this.serializerStrategy=strategy;
    }

    public String getContentType()
    {
        return contentType;
    }

    public ResponseBodySerializerStrategy getSerializerStrategy()
    {
        return serializerStrategy;
    }

    public static Optional<SerializerStrategy> serializerStrategy(String contentType)
    {
        return Stream.of(SerializerStrategy.values()).
                filter(e->e.getContentType().equals(contentType)).findFirst();
    }
}
