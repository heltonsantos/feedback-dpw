package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.repository.IAvaliacaoRepository;
import com.br.datafeed.service.IAvaliacaoService;

public class AvaliacaoService implements IAvaliacaoService{
	
	@Inject
	IAvaliacaoRepository repository;

	public void adicionarAvaliacao(Avaliacao avaliacao) {
		repository.adicionarAvaliacao(avaliacao);
		
	}

	public void atualizarAvaliacao(Avaliacao avaliacao) {
		repository.atualizarAvaliacao(avaliacao);
		
	}

}
