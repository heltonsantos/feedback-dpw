package com.br.datafeed.repository.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.repository.IFeedbackRepository;
import com.google.inject.Singleton;

@Singleton
public class FeedbackRepository implements IFeedbackRepository{

	public void adicionarFeedback(Feedback feedback) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.save(feedback);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}

	public Feedback pegarFeedback(int dataset_id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Feedback feedback;

        try{
	        feedback = (Feedback) session.createCriteria(Feedback.class).add(Restrictions.eq("dataset_id", dataset_id)).uniqueResult();
	        session.getTransaction().commit();
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return feedback;
	}

}
