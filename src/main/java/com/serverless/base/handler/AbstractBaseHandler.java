package com.serverless.base.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.base.DeserializerStrategy;
import com.serverless.base.RequestBodyDeserializerStrategy;
import com.serverless.base.ResponseBodySerializerStrategy;
import com.serverless.base.SerializerStrategy;
import com.serverless.constants.HttpMethod;
import com.serverless.exceptions.HttpException;
import com.serverless.request.ApiGatewayRequest;
import com.serverless.response.ApiGatewayResponse;
import com.serverless.response.DefaultErrorResponse;
import com.serverless.validation.DefaultValidator;
import com.serverless.validation.RequestValidator;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

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
    private Class<?> parameterizedOutput;

    public AbstractBaseHandler()
    {
        ParameterizedType type= (ParameterizedType)getClass().getGenericSuperclass();
        this.parameterizedInput=(Class<?>)type.getActualTypeArguments()[0];
        this.parameterizedOutput=(Class<?>)type.getActualTypeArguments()[1];
    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context)

    {
        ResponseBodySerializerStrategy serializerStrategy=null;
        RequestBodyDeserializerStrategy deserializerStrategy=null;
        RequestValidator validator=null;

        I input=null;
        Object output=null;
        int httpStatusCode= HttpStatus.SC_OK;
        try
        {
            //populate instance fields related to request
            initRequestAttributes(request);
            //get content type
            String contentType=getHeaders(HttpHeaders.CONTENT_TYPE).orElse(DEFAULT_CONTENT_TYPE);
            //get headers
            String accept=getHeaders(HttpHeaders.ACCEPT).orElse(DEFAULT_CONTENT_TYPE);
            //get deserializer strategy
            deserializerStrategy=resolveDeserializerStrategy(contentType);
            //get serializer strategy
            serializerStrategy=resolveSerializerStrategy(accept);
            //call before method
            before(context);
            //check if deserializable
            boolean isDeserializable=isDeserializable();
            if(isDeserializable)
            {
                input=deserializerStrategy.deserialize(rawBodyOrQueryString(),parameterizedInput);
            }
            else
            {
                input=isVoidInput()?null:(I)request;
            }

            if(shouldValidate() && isDeserializable)
            {
                validator=requestValidator();
                validator.validate(input);
            }

            output=execute(input,context);
            if(!isSerializable() && !isVoidOutput())
            {
                return (ApiGatewayResponse)output;
            }
        }
        catch (HttpException httpException)
        {
            output=httpException.getEntity();
            httpStatusCode=httpException.getHttpStatusCode();
        }
        catch(Exception exception)
        {
            output=new DefaultErrorResponse(exception.getMessage());
            httpStatusCode=HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        return ApiGatewayResponse.builder().setStatusCode(httpStatusCode)
                .setHeaders(responseHeaders).setObjectBody(output).build();
    }

    private void initRequestAttributes(ApiGatewayRequest request)
    {
        rawRequestBody=request.getBody();
        httpMethod=request.getHttpMethod();
        path=request.getBody();
        resource=request.getResource();

        headers=initOrDefaultHashMap(request.getHeaders());
        stageVariables=initOrDefaultHashMap(request.getStageVariables());
        pathParameters=initOrDefaultHashMap(request.getPathParameters());
        queryStringParameters=initOrDefaultHashMap(request.getQueryStringParameters());
        additionalProperties=initOrDefaultHashMap(request.getAdditionalProperties());
    }

    private Map<String,String> initOrDefaultHashMap(Map<String,String> map)
    {
        return Objects.isNull(map)?new HashMap<>():map;
    }

    private Optional<String> getHeaders(String key)
    {
        return Optional.ofNullable(headers.get(key));
    }

    private RequestBodyDeserializerStrategy resolveDeserializerStrategy(String contentType)
    {
        return DeserializerStrategy.strategy(contentType).orElse(DEFAULT_DESERIALIZER_STRATEGY).getDeserializerStrategy();
    }

    private ResponseBodySerializerStrategy resolveSerializerStrategy(String accept)
    {
        return SerializerStrategy.serializerStrategy(accept).orElse(DEFAULT_SERIALIZER_STRATEGY).getSerializerStrategy();
    }

    private String rawBodyOrQueryString() throws JsonProcessingException
    {
        if(!HttpMethod.GET.getName().equals(httpMethod))
            return rawRequestBody;

        return MAPPER.writeValueAsString(queryStringParameters);
    }

    private boolean isDeserializable()
    {
        return !ApiGatewayRequest.class.isAssignableFrom(parameterizedInput)
                && !Void.class.isAssignableFrom(parameterizedInput);
    }

    private boolean isSerializable()
    {
        return !ApiGatewayResponse.class.isAssignableFrom(parameterizedOutput)
                && !Void.class.isAssignableFrom(parameterizedOutput);
    }

    private boolean isVoidInput()
    {
        return Void.class.isAssignableFrom(parameterizedInput);
    }

    private boolean isVoidOutput()
    {
        return Void.class.isAssignableFrom(parameterizedOutput);
    }

    private boolean shouldValidate() throws NoSuchMethodException,SecurityException
    {
        Method method=getClass().getMethod(EXECUTE_METHOD,parameterizedInput,Context.class);
        Annotation [][] annotations=method.getParameterAnnotations();
        Annotation[] firstArgAnnotations= Arrays.stream(annotations).findFirst().get();
        return Arrays.stream(firstArgAnnotations).anyMatch(a->a instanceof Valid);
    }

    private RequestValidator requestValidator()
    {
        return DEFAULT_REQUEST_VALIDATOR;
    }

    public void addResponseHeaders(String key,String value)
    {
        responseHeaders.put(key,value);
    }

    public abstract void before(Context context) throws HttpException;
    public abstract O execute(I input,Context context) throws HttpException;

}
