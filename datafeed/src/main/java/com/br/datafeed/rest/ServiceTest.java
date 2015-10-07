package com.br.datafeed.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.br.datafeed.inject.FeedbackTestModule;
import com.br.datafeed.model.FeedbackTest;
import com.br.datafeed.service.IFeedbackTestService;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/test")
public class ServiceTest {
	
	@GET
    @Path("/get/json/")
    @Produces("application/json")
    public Response getFeedbackTest(){
		
		Injector injector = Guice.createInjector(new FeedbackTestModule());
		IFeedbackTestService servico = injector.getInstance(IFeedbackTestService.class);
		
		FeedbackTest feed = servico.getFeedbackTest();
		
		Gson gson = new Gson();
		String json = gson.toJson(feed);
		
		return Response.ok(json, MediaType.APPLICATION_JSON).build();		
    }
}
