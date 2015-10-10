package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.repository.IComentarioRepository;
import com.br.datafeed.service.IRespostaService;

public class RespostaService implements IRespostaService{
	
	@Inject
	IComentarioRepository repository;

}
