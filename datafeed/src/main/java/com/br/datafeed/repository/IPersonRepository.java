package com.br.datafeed.repository;

import com.br.datafeed.model.Person;

public interface IPersonRepository {

	public void adicionarPerson(Person person);
	public Person buscarPerson(int person_id);
	public Person buscarPersonPorEmail(String mbox);
}
