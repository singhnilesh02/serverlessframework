package com.serverless.exceptions;

import com.serverless.constants.HttpStatusCode;
import org.apache.http.HttpStatus;

public class TrackingNumberException extends HttpException
{
    public TrackingNumberException()
    {
        super(HttpStatusCode.TRACKING_NUMBER_NOTFOUND);
    }

    public TrackingNumberException(Object entity)
    {
        super(entity,HttpStatusCode.TRACKING_NUMBER_NOTFOUND);
    }

    public TrackingNumberException(final String message,Throwable cause)
    {
        super(HttpStatusCode.TRACKING_NUMBER_NOTFOUND,message,cause);
    }

    public TrackingNumberException(Object entity,final String message,Throwable cause)
    {
        super(entity,HttpStatusCode.TRACKING_NUMBER_NOTFOUND,message,cause);
    }
}
