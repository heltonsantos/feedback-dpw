package com.br.datafeed.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.br.datafeed.inject.PersonModule;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.Person;
import com.br.datafeed.rest.json.FeedbackAnnotated;
import com.br.datafeed.service.IFeedbackService;
import com.br.datafeed.service.IPersonService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/feedback")
public class FeedbackRest {
	
	Injector injector = Guice.createInjector(new FeedbackModule());
	IFeedbackService servico = injector.getInstance(IFeedbackService.class);
	
	Injector injectorPerson = Guice.createInjector(new PersonModule());
	IPersonService servicoPerson = injectorPerson.getInstance(IPersonService.class);

	
	@POST
    @Path("/adicionar")
	@Consumes("application/json")
    public Response adicionarFeedback(@QueryParam("identifier") String identifier, Feedback feedback){

		Date data = new Date();
		feedback.setDateSubmitted(data);
		
		try {	
		servico.adicionarFeedback(identifier, feedback);
		return Response.ok().build();
		
		} catch (NullPointerException e) {
			e.printStackTrace();
	    	return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();	
	    }
    }
	
	@POST
    @Path("/adicionarAnotado")
	@Consumes("application/json")
    public Response adicionarFeedbackAnotado(@QueryParam("identifier") String identifier, FeedbackAnnotated feedbackAnnotated){
		
		Feedback feedback = new Feedback();
		Person person = new Person();
		
		feedback = feedbackAnnotated.getFeedback();
		
		person = servicoPerson.buscarPersonPorEmail(feedbackAnnotated.getPerson().getMbox());
		
		if(person == null){
			servicoPerson.adicionarPerson(feedbackAnnotated.getPerson());
			feedback.setAnnotatedBy(feedbackAnnotated.getPerson());
		}
		else{
			feedback.setAnnotatedBy(person);
		}
	
		Date data = new Date();
		feedback.setDateSubmitted(data);
		
		try {	
		servico.adicionarFeedback(identifier, feedback);
		return Response.ok().build();
		
		} catch (NullPointerException e) {
			e.printStackTrace();
	    	return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();	
	    }
    }
	
	@GET
    @Path("/buscar")
    @Produces("application/json")
    public Response buscarFeedbackList(@QueryParam("identifier") String identifier, @QueryParam("offset") int offset, @QueryParam("limit") int limit){
		
		List<Feedback> feedbackList;
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		feedbackList = servico.buscarFeedbackList(identifier, offset, limit);
		
		try {
			json = mapper.writeValueAsString(feedbackList);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();		
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.EXPECTATION_FAILED).entity("Exception: " + e.toString()).build();	
		}
		
	}
	
	@DELETE
	@Path("/deletar")
	public Response deletarFeedback(@QueryParam("feedback_id") int feedback_id){
		
		servico.deletarFeedback(feedback_id);
		
		return Response.ok().build();
		
	}
}
