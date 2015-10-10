package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.repository.IComentarioRepository;
import com.br.datafeed.service.IComentarioService;

public class ComentarioService implements IComentarioService{
	
	@Inject
	IComentarioRepository repository;

}
