package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.br.datafeed.inject.PersonModule;
import com.br.datafeed.model.Person;
import com.br.datafeed.service.IPersonService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PersonTest {
	
	Injector injector = Guice.createInjector(new PersonModule());
	IPersonService servico = injector.getInstance(IPersonService.class);

	//@Test
	public void adicionarPerson() {
		
		Person person = new Person();   
        person.setGiveName("Helton");
        person.setMbox("hdas@cin.ufpe.br");
        
        servico.adicionarPerson(person);
		
	}
	
	//@Test
	public void buscarPerson() throws JsonGenerationException, JsonMappingException, IOException {
		Person person = servico.buscarPerson(1);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(person));
				
	}
	
}
