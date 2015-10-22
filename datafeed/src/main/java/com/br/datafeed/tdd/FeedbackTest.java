package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.br.datafeed.inject.FeedbackModule;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.service.IFeedbackService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class FeedbackTest {
	
	Injector injector = Guice.createInjector(new FeedbackModule());
	IFeedbackService servico = injector.getInstance(IFeedbackService.class);

	//@Test
	public void adicionarFeedback() {
		
		Feedback feedback = new Feedback();   
        feedback.setDataset_id("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
        
        servico.adicionarFeedback(feedback);
		
	}
	
	//@Test
	public void buscarFeedback() throws JsonGenerationException, JsonMappingException, IOException {
		Feedback feedback = servico.buscarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(feedback));
				
	}
	
	//@Test
	public void atualizarFeedback() {
		
		Feedback feedback = servico.buscarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
        feedback.setAvaliacao_media(9.5);
        
        servico.atualizarFeedback(feedback);
		
	}
	
	//@Test
	public void buscarFeedbackView() throws JsonGenerationException, JsonMappingException, IOException {
		Feedback feedback = servico.buscarFeedbackView("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(feedback));
				
	}
	
	//@Test
	public void buscarAdicionarFeedback() throws JsonGenerationException, JsonMappingException, IOException {
		Feedback feedback = new Feedback();
		Feedback newFeedback = new Feedback();
		
		feedback = servico.buscarFeedbackView("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		
		if(feedback == null){
			newFeedback.setDataset_id("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
			servico.adicionarFeedback(newFeedback);
		}
		else{
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(feedback));
		
		}
				
	}

}
