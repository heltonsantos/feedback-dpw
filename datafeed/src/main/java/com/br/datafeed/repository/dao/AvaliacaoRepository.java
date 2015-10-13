package com.br.datafeed.repository.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.repository.IAvaliacaoRepository;
import com.google.inject.Singleton;

@Singleton
public class AvaliacaoRepository implements IAvaliacaoRepository{

	public void adicionarAvaliacao(Avaliacao avaliacao) {

		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.save(avaliacao);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}

	public void atualizarAvaliacao(Avaliacao avaliacao) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.update(avaliacao);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}

	public Avaliacao buscarAvaliacao(int avaliacao_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Avaliacao avaliacao;

        try{
        	avaliacao = (Avaliacao) session.createCriteria(Avaliacao.class).add(Restrictions.eq("id", avaliacao_id)).uniqueResult();
	        session.getTransaction().commit();
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return avaliacao;
	}

	public void deletarAvaliacao(Avaliacao avaliacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.delete(avaliacao);;
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}

}
