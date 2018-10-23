package com.niit.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.configuration.DBConfig;
import com.niit.model.Product;

import junit.framework.TestCase;

public class ProductDaoImplTest extends TestCase {
	
	 ApplicationContext context=
	     		new AnnotationConfigApplicationContext(DBConfig.class,ProductDaoImpl.class);
	     ProductDao productDao=(ProductDao)context.getBean("productDaoImpl");

	public void testSaveOrUpdateProduct() {
		Product p=new Product();

	}

	public void testGetProduct() {
		Product product1=productDao.getProduct(2);
		Product product2=productDao.getProduct(4);
		assertNull(product1);
		assertNotNull(product2);
		assertEquals(product2.getProductname(),"Toy Car");
	}

	public void testDeleteProduct() {
		productDao.deleteProduct(6);
		Product product=productDao.getProduct(6);
		assertNull(product);
	}

	public void testGetAllProducts() {
		List<Product> products=productDao.getAllProducts();
		assertTrue("products size " + products.size(),products.size()>0);
		
	}

}
