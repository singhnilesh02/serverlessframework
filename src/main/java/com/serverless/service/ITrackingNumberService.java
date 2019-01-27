package com.serverless.service;

import com.serverless.exceptions.TrackingNumberException;
import com.serverless.request.TrackingNumberRequest;

public interface ITrackingNumberService
{
    void GenerateTrackingNumber(TrackingNumberRequest trackingNumberRequest)
            throws TrackingNumberException;
}
