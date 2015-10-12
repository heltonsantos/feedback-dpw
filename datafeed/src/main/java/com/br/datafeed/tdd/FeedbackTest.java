package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.br.datafeed.inject.FeedbackModule;
import com.br.datafeed.model.Avaliacao;
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
        feedback.setAvaliacao_media(8.5);
        
        servico.adicionarFeedback(feedback);
		
	}
	
	//@Test
	public void pegarFeedback() {
		Feedback feedback = servico.pegarFeedback(1);
		List<Avaliacao> listAvaliacao = new ArrayList<Avaliacao>(feedback.getAvaliacao());
		
		System.out.println(feedback.getDataset_id());
		System.out.println(listAvaliacao.get(0).getComentario());
				
	}
	
	//@Test
	public void atualizarFeedback() {
		
		Feedback feedback = servico.pegarFeedback(1);
		
        feedback.setAvaliacao_media(9.5);
        
        servico.atualizarFeedback(feedback);
		
	}

}