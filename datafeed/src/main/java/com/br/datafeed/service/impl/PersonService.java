package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.model.Person;
import com.br.datafeed.repository.IPersonRepository;
import com.br.datafeed.service.IPersonService;

public class PersonService implements IPersonService{

	@Inject
	IPersonRepository repository;

	public void adicionarPerson(Person person) {
		repository.adicionarPerson(person);	
	}

	public Person buscarPerson(int person_id) {
		return repository.buscarPerson(person_id);
	}

	public Person buscarPersonPorEmail(String mbox) {
		return repository.buscarPersonPorEmail(mbox);
	}

}
