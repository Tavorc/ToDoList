package com.tavor.samples.model;

import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class HibernateToDoListDAO implements IToDoListDAO{
private static HibernateToDoListDAO instance;
private static SessionFactory factory;

/**
 * constructor
 */
private HibernateToDoListDAO() {}
/** 
 * 
 * implements the design pattern Singleton 
 * return instance
 */
public static HibernateToDoListDAO getInstance() {
	if (instance == null) {
		instance = new  HibernateToDoListDAO();
		factory = new AnnotationConfiguration().configure().addAnnotatedClass(Item.class).addAnnotatedClass(User.class).buildSessionFactory();
	}
	return instance;
 }
	/**
	 * like the comment in IToDoListDAO
	 */
	public void addItem(Item item) throws ItemException
	{
		Session session = factory.openSession();
		Transaction tx =null;
		try{
			tx=session.beginTransaction();
			session.save(item);
			tx.commit();
		}
		catch(HibernateException e)
		{
			if(tx!=null) tx.rollback();
			throw new ItemException(e.getMessage());
		}
		finally{
			try{
				session.close();
			}
			catch(HibernateException e)
			{
				throw new ItemException(e.getMessage());
			}
		}
	}
	/**
	 * like the comment in IToDoListDAO
	 */
	public void deleteItem(String mail,int itemId) throws ItemException {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Item Item = (Item)session.get(Item.class, itemId);
	         if (mail.equals(Item.getMail())){
	        	 session.delete(Item); 
		         tx.commit(); 
	         } else {
	        	 throw new ItemException("Can't delete item");
	         }
	      }catch (HibernateException e) {
	         if (tx != null) tx.rollback();
	         throw new ItemException( e.getMessage());
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new ItemException(e.getMessage());
	    	 } 
	      }
	}
	/**
	 * like the comment in IToDoListDAO
	 */
	public void updateItem(int itemId,Item updatedItem) throws ItemException
	{
		Session session =factory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
			Item item = (Item)session.get(Item.class, itemId);
			if (updatedItem.getMail().equals(item.getMail()))
			 {
				  item.setDescription(updatedItem.getDescription());		         
				  session.update(item); 
			      tx.commit();
	         }
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         throw new ItemException("Update item failed");
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new ItemException("close transaction failed");
	    	 } 
	      }
	}
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * like the comment in IToDoListDAO
	 */
	public List<Item> getAllItems(String mail) throws HibernateException
	{
		Session session =factory.openSession();
		List<Item> items=null;
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			List<Item> list = session.createQuery("from "+ Item.class.getName() + " item where item.mail='" + mail +"'").list();
			items = list;
			tx.commit();
		}
		catch(HibernateException e)
		{
			if (tx!=null) tx.rollback();
	         throw new HibernateException("can't get items" + e.getMessage());
		}
		finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new HibernateException("close transaction failed" + e.getMessage());
	    	 } 
	      }
		return items;
	}
	/**
	 * like the comment in IToDoListDAO
	 */
	public User addUser(User user) throws UserException{
		Session session = factory.openSession();
		Transaction tx = null;
		int idBack = 0;
		try{
			tx = session.beginTransaction();
			idBack = (Integer) session.save(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			throw new UserException("add user failed"); 
		}finally {
			try {
				session.close();
			} catch (HibernateException e){
				throw new UserException("close failed");
			} 
		}
		if (idBack != 0) {
			return user;	
		}
		return null;
	}
	/**
	 * like the comment in IToDoListDAO
	 */
	public User getUser(String mail, String password) throws UserException{
		Session session = factory.openSession();
	      Transaction tx = null;
	      List<User> users = null;
	      try{
	         tx = session.beginTransaction();
	         users = session.createQuery("from " + User.class.getName() + " user where user.mail ='" + mail +"'").list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         	throw new UserException("login failed");
	      }finally {
	    	 try {
	    		session.close();
	    	 } catch(HibernateException e){
	    		 throw new UserException("close failed");
	    	 }
	      }
	      if (users.size() > 0) {
		      return users.get(0);	
	      }
	      return null;
	}	
}
