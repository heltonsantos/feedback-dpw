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
import com.br.datafeed.rest.json.DatasetJson;
import com.br.datafeed.service.IDatasetService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/open")
public class OpenRest {
	
	Injector injector = Guice.createInjector(new DatasetModule());
	IDatasetService servico = injector.getInstance(IDatasetService.class);
	
	@GET
    @Path("/getDatasetFeedback")
    @Produces("application/json")
    public Response getFeedback(@QueryParam("identifier") String identifier){
		DatasetJson dataset = new DatasetJson();
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		dataset = servico.buscarDatasetJson(identifier);
		
		try {
			json = mapper.writeValueAsString(dataset);
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
