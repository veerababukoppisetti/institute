/*
 * Copyright (c) 2022 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of mlops.
 *
 * mlops project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.being.institutemanagementsystem.filter.security;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class that provides functionality to adapt objects from one type to another.
 *
 * @author Admin
 */
@Slf4j
public final class Adapter {
    /**
     * Private constructor.
     */
    private Adapter() {
        // Throw illegal if anyone creates
        throw new IllegalStateException("Cannot create instances of this class");
    }

    public static <T> T adaptTo(final Object inputObject, final Class<T> targetType) {
        if (inputObject != null && targetType.isAssignableFrom(inputObject.getClass())) {
            return targetType.cast(inputObject);
        }

       // Adapter.LOGGER.warn("Unable to adapt the provided input to target type {}.", targetType.getName());
        return null;
    }

}