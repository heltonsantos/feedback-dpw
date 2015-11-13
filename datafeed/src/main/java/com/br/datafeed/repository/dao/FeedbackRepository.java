package com.br.datafeed.repository.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.Dataset;
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

	public Feedback buscarFeedback(int feedback_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Feedback feedback;

        try{
        	feedback = (Feedback) session.createCriteria(Feedback.class).add(Restrictions.eq("id", feedback_id)).uniqueResult();
	        session.getTransaction().commit();
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return feedback;
	}

	public void deletarFeedback(Feedback feedback) {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.delete(feedback);;
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Feedback> buscarFeedbackList(String identifier, int offset, int limit) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        List<Feedback> feedbackList;

        try{
        	Criteria cr = session.createCriteria(Dataset.class).createAlias("Feedback", "f").add(Restrictions.eq("identifier", identifier))
        			.addOrder(Order.desc("f.dateSubmitted"))
        				.setProjection(Projections.projectionList()
              		      .add(Projections.property("f.id"), "id")
              		      .add(Projections.property("f.dateSubmitted"), "dateSubmitted")
            		      .add(Projections.property("f.hasBody"), "hasBody")
            		      .add(Projections.property("f.motivatedBy"), "motivatedBy"))
        					.setFirstResult(offset)
        					.setMaxResults(limit)
        					.setResultTransformer(Transformers.aliasToBean(Feedback.class));;

        	 feedbackList = cr.list();
        	
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return feedbackList;
	}

}
