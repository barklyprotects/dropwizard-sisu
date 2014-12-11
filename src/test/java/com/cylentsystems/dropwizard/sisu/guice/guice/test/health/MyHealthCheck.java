package com.cylentsystems.dropwizard.sisu.guice.guice.test.health;

import javax.inject.Named;

import com.yammer.metrics.core.HealthCheck;

@Named
public class MyHealthCheck extends HealthCheck {

	public MyHealthCheck() {
		super("my-health");
	}
	
	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
