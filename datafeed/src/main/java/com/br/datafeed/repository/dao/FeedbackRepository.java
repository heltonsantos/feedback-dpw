package com.br.datafeed.repository.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

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

	public void atualizarFeedback(Feedback feedback) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.update(feedback);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}
	
	public Feedback buscarFeedback(int dataset_id) {
		
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
	
	public Feedback buscarFeedbackView(int dataset_id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Feedback feedback;

        try{
        	Criteria cr = session.createCriteria(Feedback.class).add(Restrictions.eq("dataset_id", dataset_id))
        		    .setProjection(Projections.projectionList()
        		      .add(Projections.property("id"), "id")
        		      .add(Projections.property("dataset_id"), "dataset_id")
        		      .add(Projections.property("avaliacao_media"), "avaliacao_media"))
        		    	.setResultTransformer(Transformers.aliasToBean(Feedback.class));

        	feedback = (Feedback) cr.uniqueResult();
        	
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return feedback;
	}
}
