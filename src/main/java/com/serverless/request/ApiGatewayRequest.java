
package com.serverless.request;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiGatewayRequest {

    @JsonProperty("body")
    private String body;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("queryStringParameters")
    private Map<String, String> queryStringParameters;

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("pathParameters")
    private Map<String, String> pathParameters;

    @JsonProperty("httpMethod")
    private String httpMethod;

    @JsonProperty("stageVariables")
    private Map<String, String> stageVariables;

    @JsonProperty("path")
    private String path;

    @JsonProperty("isBase64Encoded")
    private boolean base64Encoded;

    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getResource() {
        return resource;
    }


    public void setResource(String resource) {
        this.resource = resource;
    }


    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }


    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }


    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    public Map<String, String> getPathParameters() {
        return pathParameters;
    }


    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }


    public String getHttpMethod() {
        return httpMethod;
    }


    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }


    public Map<String, String> getStageVariables() {
        return stageVariables;
    }


    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public boolean isBase64Encoded() {
        return base64Encoded;
    }


    public void setBase64Encoded(boolean base64Encoded) {
        this.base64Encoded = base64Encoded;
    }


    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }


    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}
