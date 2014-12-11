package com.cylentsystems.dropwizard.sisu.guice.guice.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class SampleServiceConfiguration extends Configuration {

	@JsonProperty
	private String foo;
	
	public String getFoo() {
		return foo;
	}
	
}
