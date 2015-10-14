package com.br.datafeed.tdd;

import static org.junit.Assert.*;

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
        feedback.setDataset_id(1);
        
        servico.adicionarFeedback(feedback);
		
	}
	
	//@Test
	public void buscarFeedback() {
		Feedback feedback = servico.buscarFeedback(1);
		
		System.out.println(feedback.getDataset_id());
		System.out.println(feedback.getAvaliacao().get(0).getComentario());
				
	}
	
	//@Test
	public void atualizarFeedback() {
		
		Feedback feedback = servico.buscarFeedback(1);
		
        feedback.setAvaliacao_media(9.5);
        
        servico.atualizarFeedback(feedback);
		
	}

}
