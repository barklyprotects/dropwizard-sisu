package com.cylentsystems.dropwizard.sisu.common.resources;

import io.tesla.dropwizard.sisu.guice.test.service.MyService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Named
@Path("/common-resource")
public class CommonResource {

	private MyService myService;
	
	@Inject
	public CommonResource(MyService myService) {
		this.myService = myService;
	}
	
	@GET
	public Response doGet(){
		return Response.ok("We have so much in common!!!").build();
	}
	
	public MyService getMyService() {
		return myService;
	}
	
}

