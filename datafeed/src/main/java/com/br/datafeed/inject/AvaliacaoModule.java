package com.br.datafeed.inject;

import com.br.datafeed.repository.IAvaliacaoRepository;
import com.br.datafeed.repository.dao.AvaliacaoRepository;
import com.br.datafeed.service.IAvaliacaoService;
import com.br.datafeed.service.impl.AvaliacaoService;
import com.google.inject.AbstractModule;

public class AvaliacaoModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IAvaliacaoService.class).to(AvaliacaoService.class);
		bind(IAvaliacaoRepository.class).to(AvaliacaoRepository.class);
		
	}

}
