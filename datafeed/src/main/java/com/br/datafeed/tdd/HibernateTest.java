package com.br.datafeed.tdd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.FeedbackTest;

public class HibernateTest {

	//@Test
	public void test() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        FeedbackTest feedback = new FeedbackTest();
        feedback.setComentario("up");
        
        session.saveOrUpdate(feedback);
        session.getTransaction().commit();
        session.close();
	}
	
	//@Test
	public void adicionarFeedback() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        Feedback feedback = new Feedback();   
        feedback.setDataset_id(1);
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
		
		List<Avaliacao> list = new ArrayList<Avaliacao>(feedback.getAvaliacao());
				
	}
	
	@Test
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
