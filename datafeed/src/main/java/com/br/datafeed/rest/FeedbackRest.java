package com.br.datafeed.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Path("/buscar")
    @Produces("application/json")
    public Response buscarFeedback(@QueryParam("dataset_id") int id){
		
		Feedback feedback = new Feedback();
		Feedback newFeedback = new Feedback();
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		feedback = servico.buscarFeedbackView(id);
		
		if(feedback == null){
			newFeedback.setDataset_id(id);
			servico.adicionarFeedback(newFeedback);
			
			feedback = servico.buscarFeedbackView(id);
			
			try {
				json = mapper.writeValueAsString(feedback);
				return Response.ok(json, MediaType.APPLICATION_JSON).build();
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.EXPECTATION_FAILED).build();		
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.EXPECTATION_FAILED).build();		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.EXPECTATION_FAILED).build();		
			}
			
		}
		else{
		
			try {
				json = mapper.writeValueAsString(feedback);
				return Response.ok(json, MediaType.APPLICATION_JSON).build();
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.EXPECTATION_FAILED).build();		
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.EXPECTATION_FAILED).build();		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.EXPECTATION_FAILED).build();		
			}
		}
			
    }

}
