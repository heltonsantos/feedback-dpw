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
import com.br.datafeed.model.Comentario;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.FeedbackTest;

public class HibernateTest {

	//@Test
	public void test() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        FeedbackTest feedback = new FeedbackTest();
        
        //feedback.setId(1);
        feedback.setComentario("up");
        
        session.saveOrUpdate(feedback);
        session.getTransaction().commit();
        session.close();
	}
	
	@Test
	public void setFeedback() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        Feedback feedback = new Feedback();
        feedback.setDataset_id(2);
        feedback.setAvaliacao_media(8.5);
        session.save(feedback);
        
        Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setData_avaliacao(sqlDate);
        comentario.setNome_usuario("helton");
        comentario.setEmail_usuario("helton@gmail.com");
        comentario.setComentario("up up");
        comentario.setAvaliacao(5.5);
        comentario.setFeedback(feedback);
        session.save(comentario);
              
        session.getTransaction().commit();
        session.close();
	
	}
	//@Test
	public void getFeedback() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Feedback feedback = null;		
	
        feedback = (Feedback) session.createCriteria(Feedback.class).add(Restrictions.eq("id", 1)).uniqueResult();
        session.getTransaction().commit();
	
		session.close();
		
		List<Comentario> list = new ArrayList<Comentario>(feedback.getComentario());
				
	}

}
