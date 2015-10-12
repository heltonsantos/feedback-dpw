package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.br.datafeed.inject.FeedbackModule;
import com.br.datafeed.model.Comentario;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.service.IFeedbackService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class FeedbackTest {
	
	Injector injector = Guice.createInjector(new FeedbackModule());
	IFeedbackService servico = injector.getInstance(IFeedbackService.class);

	//@Test
	public void pegarFeedback() {
		Feedback feedback = servico.pegarFeedback(1);
		List<Comentario> listComentario = new ArrayList<Comentario>(feedback.getComentario());
		
		System.out.println(feedback.getDataset_id());
		System.out.println(listComentario.get(0).getComentario());
				
	}
	
	@Test
	public void adicionarFeedback() {
		
	}

}
