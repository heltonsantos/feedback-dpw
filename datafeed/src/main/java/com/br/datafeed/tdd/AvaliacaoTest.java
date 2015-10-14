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
			
		Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Avaliacao avaliacao = new Avaliacao();
        avaliacao.setData_avaliacao(sqlDate);
        avaliacao.setNome_usuario("helton1");
        avaliacao.setEmail_usuario("helton1@gmail.com");
        avaliacao.setComentario("up up");
        avaliacao.setAvaliacao(10.0);
        
        servicoAvaliacao.adicionarAvaliacao(1, avaliacao);
	}
	
	//@Test
	public void atualizarAvaliacao() {
		
		Feedback feedback = servicoFeedback.buscarFeedback(1);
		List<Avaliacao> list = feedback.getAvaliacao();
		
		list.get(0).setComentario("alterou");
		list.get(0).setAvaliacao(10.0);
		 
        servicoAvaliacao.atualizarAvaliacao(1, list.get(0));
	}
	
	//@Test
	public void buscarAvaliacao() {
        
        Avaliacao avaliacao = servicoAvaliacao.buscarAvaliacao(1);
        System.out.println(avaliacao.getComentario());
	}
	
	//@Test
	public void deletaAvaliacao() {
        
		servicoAvaliacao.deletarAvaliacao(1);
	}

}
