package com.serverless.base.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.base.RequestBodyDeserializerStrategy;
import com.serverless.exceptions.InternalServerException;

public class JsonRequestBodyDeserializerStrategy implements RequestBodyDeserializerStrategy
{
    @Override
    public <T> T deserialize(String body, Class<?> entityClass) throws Exception {
        ObjectMapper objectMapper =new ObjectMapper();
        try
        {
            return (T) objectMapper.readValue(body,entityClass);
        }
        catch (JsonParseException e)
        {
            throw e;
        }
        catch (JsonMappingException ex)
        {
            throw ex;
        }
        catch (Exception e)
        {
            throw new InternalServerException(e.getMessage(),e);
        }
    }
}
