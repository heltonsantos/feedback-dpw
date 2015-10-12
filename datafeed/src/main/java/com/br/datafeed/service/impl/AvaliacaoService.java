package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.repository.IAvaliacaoRepository;
import com.br.datafeed.service.IAvaliacaoService;

public class AvaliacaoService implements IAvaliacaoService{
	
	@Inject
	IAvaliacaoRepository repository;

}
