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

import com.br.datafeed.inject.AvaliacaoModule;
import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.service.IAvaliacaoService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/avaliacao")
public class AvaliacaoRest {
	
	Injector injector = Guice.createInjector(new AvaliacaoModule());
	IAvaliacaoService servico = injector.getInstance(IAvaliacaoService.class);
	
	@POST
    @Path("/adicionar")
	@Consumes("application/json")
    public Response adicionarFeedback(@QueryParam("dataset_id") String dataset_id, Avaliacao avaliacao){
		
		Date data = new Date();
		avaliacao.setData_avaliacao(data);
		
		try {	
		servico.adicionarAvaliacao(dataset_id, avaliacao);
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
    public Response buscarAvaliacaoList(@QueryParam("dataset_id") String dataset_id, @QueryParam("offset") int offset, @QueryParam("limit") int limit){
		
		List<Avaliacao> avaliacaoList;
		ObjectMapper mapper = new ObjectMapper();
		String json;
		
		avaliacaoList = servico.buscarAvaliacaoList(dataset_id, offset, limit);
		
		try {
			json = mapper.writeValueAsString(avaliacaoList);
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
	public Response deletarAvaliacao(@QueryParam("avaliacao_id") int avaliacao_id){
		
		servico.deletarAvaliacao(avaliacao_id);
		
		return Response.ok().build();
		
	}
}
