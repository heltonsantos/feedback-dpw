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
import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.model.Feedback;
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
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> buscarAvaliacaoList(int dataset_id, int offset, int limit) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        List<Avaliacao> avaliacaoList;

        try{
        	Criteria cr = session.createCriteria(Feedback.class).createAlias("avaliacao", "a").add(Restrictions.eq("dataset_id", dataset_id))
        			.addOrder(Order.desc("a.data_avaliacao"))
        				.setProjection(Projections.projectionList()
              		      .add(Projections.property("a.id"), "id")
              		      .add(Projections.property("a.data_avaliacao"), "data_avaliacao")
              		      .add(Projections.property("a.nome_usuario"), "nome_usuario")
              		      .add(Projections.property("a.email_usuario"), "email_usuario")
            		      .add(Projections.property("a.avaliacao"), "avaliacao")
            		      .add(Projections.property("a.comentario"), "comentario"))
        					.setFirstResult(offset)
        					.setMaxResults(limit)
        					.setResultTransformer(Transformers.aliasToBean(Avaliacao.class));;

        	 avaliacaoList = cr.list();
        	
        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return avaliacaoList;
	}

}
