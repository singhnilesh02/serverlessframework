package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.base.handler.AbstractBaseHandler;
import com.serverless.exceptions.HttpException;
import com.serverless.request.TrackingNumberRequest;
import com.serverless.response.TrackingNumberResponse;

public class GenerateTrackingNumber extends AbstractBaseHandler<TrackingNumberRequest, TrackingNumberResponse>
{

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeaders("Access-Control-Allow-Origin","*");
    }

    @Override
    public TrackingNumberResponse execute(TrackingNumberRequest input, Context context) throws HttpException {
        return new TrackingNumberResponse(input.getTrackingNumber(),2);
    }
}
