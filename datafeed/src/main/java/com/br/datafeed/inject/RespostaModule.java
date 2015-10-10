package com.br.datafeed.inject;

import com.br.datafeed.repository.IRespostaRepository;
import com.br.datafeed.repository.dao.RespostaRepository;
import com.br.datafeed.service.IRespostaService;
import com.br.datafeed.service.impl.RespostaService;
import com.google.inject.AbstractModule;

public class RespostaModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IRespostaService.class).to(RespostaService.class);
		bind(IRespostaRepository.class).to(RespostaRepository.class);
		
	}

}
