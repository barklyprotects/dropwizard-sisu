package com.cylentsystems.dropwizard.sisu.guice.guice.test.health;

import com.cylentsystems.dropwizard.sisu.SisuHealthCheck;
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
