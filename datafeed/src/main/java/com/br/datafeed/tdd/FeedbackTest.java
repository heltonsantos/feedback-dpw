package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.br.datafeed.inject.FeedbackModule;
import com.br.datafeed.inject.PersonModule;
import com.br.datafeed.inject.DatasetModule;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.Person;
import com.br.datafeed.rest.json.FeedbackAnnotatedView;
import com.br.datafeed.model.Dataset;
import com.br.datafeed.service.IFeedbackService;
import com.br.datafeed.service.IPersonService;
import com.br.datafeed.service.IDatasetService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class FeedbackTest {
	
	Injector injectorFeedback = Guice.createInjector(new FeedbackModule());
	IFeedbackService servicoFeedback = injectorFeedback.getInstance(IFeedbackService.class);
	
	Injector injectorDataset = Guice.createInjector(new DatasetModule());
	IDatasetService servicoDataset = injectorDataset.getInstance(IDatasetService.class);
	
	Injector injectorPerson = Guice.createInjector(new PersonModule());
	IPersonService servicoPerson = injectorPerson.getInstance(IPersonService.class);

	//@Test
	public void adicionarFeedbackRating() {
			
		Date date = new Date();
		
		Feedback feedback = new Feedback();
        feedback.setDateSubmitted(date);
        feedback.setHasBody("4.5");
        feedback.setMotivatedBy("RATING");
        
        servicoFeedback.adicionarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", feedback);
	}
	
	//@Test
	public void atualizarFeedbackRating() {
		
		Dataset dataset = servicoDataset.buscarDataset("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		List<Feedback> list = dataset.getFeedback();
		
		list.get(0).setHasBody("3.0");;
		list.get(0).setMotivatedBy("RATING");
		 
        servicoFeedback.atualizarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", list.get(0));
	}
	

	//@Test
	public void adicionarFeedbackCorrection() {
			
		Date date = new Date();
		
		Feedback feedback = new Feedback();
        feedback.setDateSubmitted(date);
        feedback.setHasBody("Defeito relatado");
        feedback.setMotivatedBy("CORRECTION");
        
        servicoFeedback.adicionarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", feedback);
	}
	
	//@Test
	public void atualizarFeedbackCorrection() {
		
		Dataset dataset = servicoDataset.buscarDataset("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
		List<Feedback> list = dataset.getFeedback();
		
		list.get(0).setHasBody("Defeito");;
		list.get(0).setMotivatedBy("CORRECTION");
		 
        servicoFeedback.atualizarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", list.get(0));
	}
	
	
	//@Test
	public void buscarFeedback() {
        
        Feedback feedback = servicoFeedback.buscarFeedback(1);
        System.out.println(feedback.getHasBody());
	}
	
	//@Test
	public void deletaFeedback() {
        
		servicoFeedback.deletarFeedback(1);
	}
	
	//@Test
	public void buscarFeedbackList() {
		List<Feedback> feedbackList;
		ObjectMapper mapper = new ObjectMapper();
		
		feedbackList = servicoFeedback.buscarFeedbackList("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", 0, 0);
		
		try {
			System.out.println(mapper.writeValueAsString(feedbackList));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void adicionarFeedbackAnotado() {
        Person person = new Person();
		
		person = servicoPerson.buscarPersonPorEmail("hdas@cin.ufpe.br");
		Date date = new Date();
		Feedback feedback = new Feedback();
        feedback.setDateSubmitted(date);
        feedback.setHasBody("4.5");
        feedback.setMotivatedBy("RATING");
        feedback.setAnnotatedBy(person);
        
        servicoFeedback.adicionarFeedback("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", feedback);
	}
	
	//@Test
	public void buscarFeedbackAnotado() {
		List<FeedbackAnnotatedView> list;
		ObjectMapper mapper = new ObjectMapper();
		
		list = servicoFeedback.buscarFeedbackListAnnotated("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28", 0, 0);
		
		try {
			System.out.println(mapper.writeValueAsString(list));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
