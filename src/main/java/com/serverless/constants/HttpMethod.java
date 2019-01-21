package com.serverless.constants;

public enum HttpMethod
{
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    TRACE("TRACE"),
    OPTIONS("OPTIONS"),
    HEAD("HEAD"),
    CONNECT("CONNECT");

    private String method;

    HttpMethod(String method)
    {
        this.method=method;
    }
    public String getName()
    {
        return this.method;
    }
}
