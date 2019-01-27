package com.serverless.exceptions;

import com.serverless.constants.HttpStatusCode;

public class ServiceMasterException extends HttpException
{
    public ServiceMasterException()
    {
        super(HttpStatusCode.SERVICE_NOTFOUND);
    }

    public ServiceMasterException(Object entity)
    {
        super(entity,HttpStatusCode.SERVICE_NOTFOUND);
    }

    public ServiceMasterException(final String message,Throwable cause)
    {
        super(HttpStatusCode.SERVICE_NOTFOUND,message,cause);
    }

    public ServiceMasterException(Object entity,final String message,Throwable cause)
    {
        super(entity,HttpStatusCode.SERVICE_NOTFOUND,message,cause);
    }
}
