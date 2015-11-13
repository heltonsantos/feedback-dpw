package com.br.datafeed.inject;

import com.br.datafeed.repository.IPersonRepository;
import com.br.datafeed.repository.dao.PersonRepository;
import com.br.datafeed.service.IPersonService;
import com.br.datafeed.service.impl.PersonService;
import com.google.inject.AbstractModule;

public class PersonModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IPersonService.class).to(PersonService.class);
		bind(IPersonRepository.class).to(PersonRepository.class);
		
	}

}
