package com.br.datafeed.tdd;

import org.hibernate.Session;
import org.junit.Test;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.FeedbackTest;

public class HibernateTest {

	@Test
	public void test() {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        FeedbackTest feedback = new FeedbackTest();
        
        //feedback.setId(1);
        feedback.setComentario("up");
        
        session.saveOrUpdate(feedback);
        session.getTransaction().commit();
	}

}
