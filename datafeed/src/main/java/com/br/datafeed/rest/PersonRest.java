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

import com.br.datafeed.inject.DatasetModule;
import com.br.datafeed.inject.PersonModule;
import com.br.datafeed.model.Dataset;
import com.br.datafeed.model.Person;
import com.br.datafeed.service.IDatasetService;
import com.br.datafeed.service.IPersonService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/person")
public class PersonRest {
	
	Injector injector = Guice.createInjector(new PersonModule());
	IPersonService servico = injector.getInstance(IPersonService.class);
	
	@GET
    @Path("/buscar")
    @Produces("application/json")
    public Response buscarPerson(@QueryParam("person_id") int person_id){
		
		Person person = new Person();
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		person = servico.buscarPerson(person_id);
			
		try {
			json = mapper.writeValueAsString(person);
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

}
