package com.serverless.base.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.base.DeserializerStrategy;
import com.serverless.base.RequestBodyDeserializerStrategy;
import com.serverless.base.ResponseBodySerializerStrategy;
import com.serverless.base.SerializerStrategy;
import com.serverless.exceptions.HttpException;
import com.serverless.request.ApiGatewayRequest;
import com.serverless.response.ApiGatewayResponse;
import com.serverless.validation.DefaultValidator;
import com.serverless.validation.RequestValidator;
import org.apache.http.entity.ContentType;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractBaseHandler<I,O> implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse>
{

    public static final DeserializerStrategy DEFAULT_DESERIALIZER_STRATEGY=DeserializerStrategy.APPLICATION_JSON;
    public static final SerializerStrategy DEFAULT_SERIALIZER_STRATEGY=SerializerStrategy.APPLICATION_JSON;
    public static final RequestValidator DEFAULT_REQUEST_VALIDATOR=new DefaultValidator();
    public static final String DEFAULT_CONTENT_TYPE= ContentType.APPLICATION_JSON.getMimeType();


    private Class<?> parameterizedInput;
    private Class<?> getParameterizedOutput;

    public AbstractBaseHandler()
    {
        ParameterizedType type= (ParameterizedType)getClass().getGenericSuperclass();
        this.parameterizedInput=(Class<?>)type.getActualTypeArguments()[0];
        this.getParameterizedOutput=(Class<?>)type.getActualTypeArguments()[1];
    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context)
    {
        ResponseBodySerializerStrategy serializerStrategy=null;
        RequestBodyDeserializerStrategy deserializerStrategy=null;
        RequestValidator validator=null;

        I input=null;
        Object output=null;
        try
        {

        }
        /*catch (HttpException httpException)
        {

        }*/
        catch(Exception exception)
        {

        }
        return null;
    }


}
