package com.serverless.service;

import com.serverless.exceptions.TrackingNumberException;
import com.serverless.request.TrackingNumberRequest;
import com.serverless.response.TrackingNumberResponse;

public interface ITrackingNumberService
{
    TrackingNumberResponse GenerateTrackingNumber(TrackingNumberRequest trackingNumberRequest)
            throws TrackingNumberException;
}
