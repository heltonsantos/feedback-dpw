package com.br.datafeed.repository.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.br.datafeed.hibernate.util.HibernateUtil;
import com.br.datafeed.model.Person;
import com.br.datafeed.repository.IPersonRepository;
import com.google.inject.Singleton;

@Singleton
public class PersonRepository implements IPersonRepository{

	public void adicionarPerson(Person person) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        
        try{
	        session.save(person);
	        session.getTransaction().commit();
        
        }catch(HibernateException e){
        	session.getTransaction().rollback();
		}
        
        session.close();
		
	}

	public Person buscarPerson(int person_id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Person person;

        try{
	        person = (Person) session.createCriteria(Person.class).add(Restrictions.eq("id", person_id)).uniqueResult();
	        session.getTransaction().commit();

        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return person;
	}
	
	public Person buscarPersonPorEmail(String mbox) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	 
        session.beginTransaction();
        Person person;

        try{
	        person = (Person) session.createCriteria(Person.class).add(Restrictions.eq("mbox", mbox)).uniqueResult();
	        session.getTransaction().commit();

        }catch(HibernateException e){
        	session.getTransaction().rollback();
        	return null;
        }
		session.close();
		
		return person;
	}
}
