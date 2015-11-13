package com.br.datafeed.service;

import com.br.datafeed.model.Person;

public interface IPersonService {
	
	public void adicionarPerson(Person dataset);
	public Person buscarPerson(int person_id);
}
