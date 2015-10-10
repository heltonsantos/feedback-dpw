package com.br.datafeed.inject;

import com.br.datafeed.repository.IComentarioRepository;
import com.br.datafeed.repository.dao.ComentarioRepository;
import com.br.datafeed.service.IComentarioService;
import com.br.datafeed.service.impl.ComentarioService;
import com.google.inject.AbstractModule;

public class ComentarioModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IComentarioService.class).to(ComentarioService.class);
		bind(IComentarioRepository.class).to(ComentarioRepository.class);
		
	}

}
