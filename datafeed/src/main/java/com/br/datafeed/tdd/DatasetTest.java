package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.br.datafeed.inject.DatasetModule;
import com.br.datafeed.model.Dataset;
import com.br.datafeed.rest.json.DatasetJson;
import com.br.datafeed.service.IDatasetService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DatasetTest {
	
	Injector injector = Guice.createInjector(new DatasetModule());
	IDatasetService servico = injector.getInstance(IDatasetService.class);

	//@Test
	public void adicionarDataset() {
		
		Dataset dataset = new Dataset();   
        dataset.setIdentifier("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
        
        servico.adicionarDataset(dataset);
		
	}
	
	//@Test
	public void buscarDataset() throws JsonGenerationException, JsonMappingException, IOException {
		Dataset dataset = servico.buscarDataset("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dataset));
				
	}
	
	//@Test
	public void atualizarDataset() {
		
		Dataset dataset = servico.buscarDataset("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
        dataset.setHasRating(5.0);
        
        servico.atualizarDataset(dataset);
		
	}
	
	//@Test
	public void buscarDatasetView() throws JsonGenerationException, JsonMappingException, IOException {
		Dataset dataset = servico.buscarDatasetView("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dataset));
				
	}
	
	//@Test
	public void buscarAdicionarDataset() throws JsonGenerationException, JsonMappingException, IOException {
		Dataset dataset = new Dataset();
		Dataset newDataset = new Dataset();
		
		dataset = servico.buscarDatasetView("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		if(dataset == null){
			newDataset.setIdentifier("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
			servico.adicionarDataset(newDataset);
		}
		else{
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dataset));
		
		}
				
	}
	
	//@Test
	public void buscarDatasetJson() throws JsonGenerationException, JsonMappingException, IOException {
		DatasetJson dataset = servico.buscarDatasetJson("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dataset));
				
	}

}
