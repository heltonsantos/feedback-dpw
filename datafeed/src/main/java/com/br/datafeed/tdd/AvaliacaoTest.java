package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.br.datafeed.inject.AvaliacaoModule;
import com.br.datafeed.inject.FeedbackModule;
import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.service.IAvaliacaoService;
import com.br.datafeed.service.IFeedbackService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AvaliacaoTest {
	
	Injector injectorAvaliacao = Guice.createInjector(new AvaliacaoModule());
	IAvaliacaoService servicoAvaliacao = injectorAvaliacao.getInstance(IAvaliacaoService.class);
	
	Injector injectorFeedback = Guice.createInjector(new FeedbackModule());
	IFeedbackService servicoFeedback = injectorFeedback.getInstance(IFeedbackService.class);

	//@Test
	public void adicionarAvaliacao() {
		
		Feedback feedback = servicoFeedback.pegarFeedback(1);
		
		Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Avaliacao avaliacao = new Avaliacao();
        avaliacao.setData_avaliacao(sqlDate);
        avaliacao.setNome_usuario("helton1");
        avaliacao.setEmail_usuario("helton1@gmail.com");
        avaliacao.setComentario("up up");
        avaliacao.setAvaliacao(5.5);
        avaliacao.setFeedback(feedback);
        
        servicoAvaliacao.adicionarAvaliacao(avaliacao);
	}
	
	//@Test
	public void atualizarAvaliacao() {
		
		Feedback feedback = servicoFeedback.pegarFeedback(1);
		List<Avaliacao> listAvaliacao = new ArrayList<Avaliacao>(feedback.getAvaliacao());
		
		listAvaliacao.get(0).setComentario("alterou");
        
        servicoAvaliacao.atualizarAvaliacao(listAvaliacao.get(0));
	}

}
