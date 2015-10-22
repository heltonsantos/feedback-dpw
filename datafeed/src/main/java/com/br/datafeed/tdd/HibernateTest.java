package com.br.datafeed.tdd;

import java.sql.Date;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.model.Feedback;

public class HibernateTest {

	//@Test
	public void adicionarFeedback() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        Feedback feedback = new Feedback();   
        feedback.setDataset_id("http://www.dadosabertosbrasil.com.br/?p=dataset&id=1577&dtId=28");
        feedback.setAvaliacao_media(8.5);
        session.save(feedback);
              
        session.getTransaction().commit();
        session.close();
	
	}
	//@Test
	public void pegarFeedback() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Feedback feedback = null;		
        
        feedback = (Feedback) session.createCriteria(Feedback.class).add(Restrictions.eq("id", 1)).uniqueResult();
        session.getTransaction().commit();
	
		session.close();
				
	}
	
	//@Test
	public void adicionarFeedbackComAvaliacao() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        Feedback feedback = (Feedback) session.createCriteria(Feedback.class).add(Restrictions.eq("id", 1)).uniqueResult();
        
        Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setData_avaliacao(sqlDate);
        avaliacao.setNome_usuario("helton");
        avaliacao.setEmail_usuario("helton@gmail.com");
        avaliacao.setComentario("up up");
        avaliacao.setAvaliacao(5.5);
        avaliacao.setFeedback(feedback);
        session.save(avaliacao);
              
        session.getTransaction().commit();
        session.close();
	
	}

}
