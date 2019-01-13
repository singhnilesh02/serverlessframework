package com.serverless.base.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.serverless.base.ResponseBodySerializerStrategy;
import com.serverless.exceptions.InternalServerException;

public class JsonResponseBodySerializerStrategy implements ResponseBodySerializerStrategy
{

    @Override
    public String serialize(Object entity) throws Exception {
        String json=null;
        try
        {
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
            json=objectMapper.writeValueAsString(entity);
        }
        catch(JsonParseException exception)
        {
            throw new InternalServerException(exception.getMessage(),exception);
        }
        return json;
    }
}
