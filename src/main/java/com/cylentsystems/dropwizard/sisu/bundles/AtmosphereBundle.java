package com.cylentsystems.dropwizard.sisu.bundles;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRegistration;

/**
 * *****************************************************************************
 * Copyright (c) 2014
 * All rights reserved.
 * Contributors: rberg
 * Cylent Systems - initial API and implementation
 * *****************************************************************************
 */
public class AtmosphereBundle implements Bundle {
    private static final Logger logger = LoggerFactory.getLogger(AtmosphereBundle.class);
    private static final String DEFAULT_PATH = "/websockets/*";
    private static final String DEFAULT_PACKAGE = "com.barkly.resources.websocket";
    private static final String DEFAULT_MIME_TYPE = "application/json";
    private static final String DEFAULT_SERVLET_NAME = "websocket";

    private final String path;
    private final String pkg;
    private final String mimeType;
    private final String name;

    public AtmosphereBundle() {
        this(DEFAULT_PATH,DEFAULT_PACKAGE,DEFAULT_MIME_TYPE,DEFAULT_SERVLET_NAME);
    }

    public AtmosphereBundle(String path) {
        this(path,DEFAULT_PACKAGE,DEFAULT_MIME_TYPE,DEFAULT_SERVLET_NAME);
    }

    public AtmosphereBundle(String path, String pkg) {
        this(path,pkg,DEFAULT_MIME_TYPE,DEFAULT_SERVLET_NAME);
    }

    public AtmosphereBundle(String path, String pkg, String mimeType) {
        this(path,pkg,mimeType,DEFAULT_SERVLET_NAME);
    }

    public AtmosphereBundle(String path, String pkg, String mimeType, String name) {
        this.path = path;
        this.pkg = pkg;
        this.mimeType = mimeType;
        this.name = name;
    }


    public void initialize(Bootstrap<?> bootstrap) {
        //nada nada
    }

    public void run(Environment environment) {
        AtmosphereServlet servlet = new AtmosphereServlet();
        servlet.framework().addInitParameter("com.sun.jersey.config.property.packages", this.pkg);
        servlet.framework().addInitParameter(ApplicationConfig.WEBSOCKET_CONTENT_TYPE, this.mimeType);
        servlet.framework().addInitParameter(ApplicationConfig.WEBSOCKET_SUPPORT, "true");

        ServletRegistration.Dynamic servletHolder = environment.servlets().addServlet(this.name, servlet);
        servletHolder.addMapping(this.path);

    }
}
