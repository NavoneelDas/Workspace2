package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.Category;
import com.niit.model.Product;
@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Product saveOrUpdateProduct(Product product) {
		System.out.println("ID " + product.getId());
	    Session session=sessionFactory.getCurrentSession();
	    session.saveOrUpdate(product);
	    System.out.println("ID " + product.getId());
		
		return product;
	}
	
	public Product getProduct(int id) {
		Session session=sessionFactory.getCurrentSession();
		Product product=(Product)session.get(Product.class, id);
		
		return product;
	}
	public void deleteProduct(int id) {
		Session session=sessionFactory.getCurrentSession();
		Product product=(Product)session.get(Product.class, id);
		if(product!=null)
		session.delete(product);
	}
	public List<Product> getAllProducts() {
		Session session=sessionFactory.getCurrentSession();
		
		
		Query query=session.createQuery("from Product");
		List<Product> products=query.list();
		return products;
	}
	public List<Category> getAllCategories() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Category");
		return query.list();
		
	}

}
