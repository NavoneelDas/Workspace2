package com.niit.backend_proj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.configuration.DBConfig;
import com.niit.dao.ProductDao;
import com.niit.dao.ProductDaoImpl;
import com.niit.model.Product;



public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext context=
        		new AnnotationConfigApplicationContext(DBConfig.class,ProductDaoImpl.class);
        ProductDao productDao=(ProductDao)context.getBean("productDaoImpl");
        Product prod=new Product();
        prod.setProductname("Mobile");
        prod.setDescription("Black Color - 5g");
        prod.setQuantity(1);
        prod.setPrice(10);
        Product product=productDao.saveOrUpdateProduct(prod);
        System.out.println(product.getId());
    }
}
