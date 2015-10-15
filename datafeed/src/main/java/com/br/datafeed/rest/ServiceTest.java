package com.br.datafeed.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.br.datafeed.inject.FeedbackTestModule;
import com.br.datafeed.model.FeedbackTest;
import com.br.datafeed.service.IFeedbackTestService;
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
		
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		try {
			json = mapper.writeValueAsString(feed);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();		
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();		
		}
		
		
    }
}
