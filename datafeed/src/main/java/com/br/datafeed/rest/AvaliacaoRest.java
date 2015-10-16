package com.br.datafeed.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

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
    public Response adicionarFeedback(@QueryParam("dataset_id") int dataset_id, Avaliacao avaliacao){
		
		Date data = new Date();
		avaliacao.setData_avaliacao(data);
		
		servico.adicionarAvaliacao(dataset_id, avaliacao);
		
		return Response.ok().build();
				
    }
}
