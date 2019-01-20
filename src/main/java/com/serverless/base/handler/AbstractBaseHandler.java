package com.serverless.base.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.base.RequestBodyDeserializerStrategy;
import com.serverless.base.ResponseBodySerializerStrategy;
import com.serverless.request.ApiGatewayRequest;
import com.serverless.response.ApiGatewayResponse;
import com.serverless.validation.RequestValidator;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractBaseHandler<I,O> implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse>
{
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
        return null;
    }


}
