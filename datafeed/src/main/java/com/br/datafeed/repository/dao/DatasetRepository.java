package com.br.datafeed.repository.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Dataset;
import com.br.datafeed.repository.IDatasetRepository;
import com.google.inject.Singleton;

@Singleton
public class DatasetRepository implements IDatasetRepository{

	public void adicionarDataset(Dataset dataset) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.save(dataset);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}

	public void atualizarDataset(Dataset dataset) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.update(dataset);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}
	
	public Dataset buscarDataset(String identifier) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Dataset dataset;

        try{
	        dataset = (Dataset) session.createCriteria(Dataset.class).add(Restrictions.eq("identifier", identifier)).uniqueResult();
	        session.getTransaction().commit();

        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return dataset;
	}
	
	public Dataset buscarDatasetView(String identifier) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Dataset dataset;

        try{
        	Criteria cr = session.createCriteria(Dataset.class).add(Restrictions.eq("identifier", identifier))
        		    .setProjection(Projections.projectionList()
        		      .add(Projections.property("id"), "id")
        		      .add(Projections.property("identifier"), "identifier")
        		      .add(Projections.property("hasRating"), "hasRating"))
        		    	.setResultTransformer(Transformers.aliasToBean(Dataset.class));

        	dataset = (Dataset) cr.uniqueResult();
        	
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return dataset;
	}
}
