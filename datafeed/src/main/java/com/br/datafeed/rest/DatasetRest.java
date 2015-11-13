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
import com.br.datafeed.model.Dataset;
import com.br.datafeed.service.IDatasetService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/dataset")
public class DatasetRest {
	
	Injector injector = Guice.createInjector(new DatasetModule());
	IDatasetService servico = injector.getInstance(IDatasetService.class);
	
	@GET
    @Path("/buscar")
    @Produces("application/json")
    public Response buscarDataset(@QueryParam("identifier") String identifier, @QueryParam("title") String title){
		
		Dataset dataset = new Dataset();
		Dataset newDataset = new Dataset();
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		dataset = servico.buscarDatasetView(identifier);
		
		if(dataset == null){
			newDataset.setIdentifier(identifier);
			newDataset.setTitle(title);
			servico.adicionarDataset(newDataset);
			
			dataset = servico.buscarDatasetView(identifier);
			
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
		else{
		
			try {
				json = mapper.writeValueAsString(dataset);
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
