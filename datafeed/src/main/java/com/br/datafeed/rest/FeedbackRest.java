package com.br.datafeed.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.br.datafeed.inject.FeedbackModule;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.service.IFeedbackService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/feedback")
public class FeedbackRest {
	
	Injector injector = Guice.createInjector(new FeedbackModule());
	IFeedbackService servico = injector.getInstance(IFeedbackService.class);
	
	@GET
    @Path("/buscar/{id}")
    @Produces("application/json")
    public Response buscarFeedback(@PathParam("id") int id){
		
		Feedback feed = servico.buscarFeedback(id);
		
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
	
	@POST
    @Path("/adicionar")
    @Consumes("application/json")
    public Response adicionarFeedback(Feedback feedback){
	
		servico.adicionarFeedback(feedback);
		
		return Response.ok().build();
				
    }

}
