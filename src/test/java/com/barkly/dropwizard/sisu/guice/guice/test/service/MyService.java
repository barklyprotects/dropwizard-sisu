package com.barkly.dropwizard.sisu.guice.guice.test.service;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MyService {

	private MyOtherService myOtherService;
	
	@Inject
	public MyService(MyOtherService myOtherService) {
		this.myOtherService = myOtherService;
	}
	
	public MyOtherService getMyOtherService() {
		return myOtherService;
	}
	
}
