package com.serverless.base.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBaseHandler<I,O> implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse>
{

    public static final DeserializerStrategy DEFAULT_DESERIALIZER_STRATEGY=DeserializerStrategy.APPLICATION_JSON;
    public static final SerializerStrategy DEFAULT_SERIALIZER_STRATEGY=SerializerStrategy.APPLICATION_JSON;
    public static final RequestValidator DEFAULT_REQUEST_VALIDATOR=new DefaultValidator();
    public static final String DEFAULT_CONTENT_TYPE= ContentType.APPLICATION_JSON.getMimeType();
    public static final String DEFAULT_ERROR_MESSAGE="Unable to process the request";
    private static final ObjectMapper MAPPER=new ObjectMapper();
    private static final String EXECUTE_METHOD="execute";
    private Map<String, String> headers;
    private Map<String, String> stageVariables;
    private String rawRequestBody;
    private String httpMethod;
    private String path;
    private Map<String, String> pathParameters;
    private Map<String, String> queryStringParameters;
    private String resource;
    private Map<String, String> additionalProperties;
    private Map<String, String> responseHeaders = new HashMap<>();


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
            //populate instance fields related to request
            //get content type
            //get headers
            //get deserializer strategy
            //get serializer strategy
            //call before method
            //check if deserializerable
            //if deserializerable deserialize else check input is not void
        }
        /*catch (HttpException httpException)
        {

        }*/
        catch(Exception exception)
        {

        }
        return null;
    }

    private void initRequestAttributes(ApiGatewayRequest request)
    {
        rawRequestBody=request.getBody();
        httpMethod=request.getHttpMethod();
        path=request.getBody();
        resource=request.getResource();
    }

    public abstract void before(Context context) throws HttpException;
    public abstract O execute(I input,Context context) throws HttpException;


}
