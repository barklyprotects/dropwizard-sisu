package com.barkly.dropwizard.sisu;

import com.codahale.metrics.health.HealthCheck;

/**
 * *****************************************************************************
 * Copyright (c) 2014
 * All rights reserved.
 * Contributors: rberg
 * Cylent Systems - initial API and implementation
 * *****************************************************************************
 */
public class SisuHealthCheck  extends HealthCheck {
    private String name;

    public SisuHealthCheck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Result check() throws Exception {
        return null;
    }
}
