package com.barkly.dropwizard.sisu.guice.guice.test.health;

import com.barkly.dropwizard.sisu.SisuHealthCheck;

import javax.inject.Named;

@Named
public class MyHealthCheck extends SisuHealthCheck {

	public MyHealthCheck() {
		super("my-health");
	}
	
	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
