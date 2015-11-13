package com.br.datafeed.tdd;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.Dataset;

public class HibernateTest {

	//@Test
	public void adicionarDataset() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        Dataset dataset = new Dataset();   
        dataset.setIdentifier("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
        dataset.setHasRating(8.5);
        session.save(dataset);
              
        session.getTransaction().commit();
        session.close();
	
	}
	//@Test
	public void buscarDataset() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Dataset dataset = null;		
        
        dataset = (Dataset) session.createCriteria(Dataset.class).add(Restrictions.eq("id", 1)).uniqueResult();
        session.getTransaction().commit();
	
		session.close();
				
	}
	
	//@Test
	public void adicionarDatasetComAvaliacao() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        Dataset dataset = (Dataset) session.createCriteria(Dataset.class).add(Restrictions.eq("id", 1)).uniqueResult();
        
        Date date = new Date();
        
        Feedback feedback = new Feedback();
        feedback.setDateSubmitted(date);
        feedback.setHasBody("4.5");
        feedback.setMotivatedBy("RATING");
        feedback.setHasTarget(dataset);
        session.save(feedback);
              
        session.getTransaction().commit();
        session.close();
	
	}

}
