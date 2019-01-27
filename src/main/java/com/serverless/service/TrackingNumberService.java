package com.serverless.service;

import com.serverless.exceptions.TrackingNumberException;
import com.serverless.request.TrackingNumberRequest;
import com.serverless.response.TrackingNumberResponse;

public class TrackingNumberService implements ITrackingNumberService
{

    @Override
    public TrackingNumberResponse GenerateTrackingNumber(TrackingNumberRequest trackingNumberRequest)
            throws TrackingNumberException
    {
        return null;
    }
}
