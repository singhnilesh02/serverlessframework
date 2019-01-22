package com.serverless.request;

public class TrackingNumberRequest
{
    private String trackingNumber;

    public TrackingNumberRequest()
    {

    }

    public void setTrackingNumber(String trackingNumber)
    {
        this.trackingNumber=trackingNumber;
    }

    public String getTrackingNumber()
    {
        return this.trackingNumber;
    }
}
