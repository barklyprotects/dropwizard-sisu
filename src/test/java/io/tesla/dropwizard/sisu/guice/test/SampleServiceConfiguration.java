package io.tesla.dropwizard.sisu.guice.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class SampleServiceConfiguration extends Configuration {

	@JsonProperty
	private String foo;
	
	public String getFoo() {
		return foo;
	}
	
}
