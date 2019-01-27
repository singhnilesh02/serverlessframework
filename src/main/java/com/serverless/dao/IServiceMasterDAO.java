package com.serverless.dao;

import com.serverless.entity.ServiceDTO;
import com.serverless.exceptions.ServiceMasterException;

public interface IServiceMasterDAO
{
    ServiceDTO getServiceMaster() throws ServiceMasterException;
}
