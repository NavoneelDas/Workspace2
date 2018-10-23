package com.niit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Product;
import com.niit.model.Category;
import com.niit.model.User;
import com.niit.model.Authorities;
import com.niit.model.Customer;
import com.niit.model.BillingAddress;
import com.niit.model.ShippingAddress;
import com.niit.model.CartItem;
import com.niit.model.CustomerOrder;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
@EnableTransactionManagement  

public class DBConfig {
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		System.out.println("Entering DataSource Bean creation method ");
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.h2.Driver");
	    dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("sa");
	    System.out.println("DataSource bean " +dataSource);
	    return dataSource;
	}
	
	@Bean 
	public SessionFactory sessionFactory() {
		System.out.println("Entering sessionFactory creation method");
		LocalSessionFactoryBuilder lsf=
				new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty(
				"hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		
		Class classes[]=new Class[]{Product.class,Category.class,User.class,Authorities.class,Customer.class,BillingAddress.class,ShippingAddress.class,CartItem.class,CustomerOrder.class};
		
		System.out.println("SessionFactory bean " + lsf);
	    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}
	@Bean
	public HibernateTransactionManager hibTransManagement(){
		return new HibernateTransactionManager(sessionFactory());
	}
}






