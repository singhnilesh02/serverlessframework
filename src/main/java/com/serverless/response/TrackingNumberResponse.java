package com.serverless.response;

public class TrackingNumberResponse
{
    private String trackingNumber;
    private int shipmentDocumentId;

    public TrackingNumberResponse(String trackingNumber,int shipmentDocumentId)
    {
        this.trackingNumber=trackingNumber;
        this.shipmentDocumentId=shipmentDocumentId;
    }

    public void setTrackingNumber(String trackingNumber)
    {
        this.trackingNumber=trackingNumber;
    }

    public String getTrackingNumber()
    {
        return this.trackingNumber;
    }

    public void setShipmentDocumentId(int shipmentDocumentId)
    {
        this.shipmentDocumentId=shipmentDocumentId;
    }

    public int getShipmentDocumentId()
    {
        return this.shipmentDocumentId;
    }
}
