package com.barkly.dropwizard.sisu.guice.guice.test.resources;

import com.barkly.dropwizard.sisu.guice.guice.test.service.MyService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Named
@Path("/my-resource")
public class MyResource {

	private MyService myService;
	
	@Inject
	public MyResource(MyService myService) {
		this.myService = myService;
	}
	
	@GET
	public Response doGet(){
		return Response.ok("hello world!").build();
	}
	
	public MyService getMyService() {
		return myService;
	}
	
}
