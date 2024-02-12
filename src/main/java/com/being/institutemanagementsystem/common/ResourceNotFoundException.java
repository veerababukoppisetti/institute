package com.being.institutemanagementsystem.common;

import org.hibernate.service.spi.ServiceException;

public class ResourceNotFoundException extends ServiceException {


    public ResourceNotFoundException(IError error, final Object... errorMessageArguments) {
        super(error.name());
    }

    public static ResourceNotFoundException instance(final IError error, final Object... errorMessageArguments) {
        return new ResourceNotFoundException(error, errorMessageArguments);
    }
}
