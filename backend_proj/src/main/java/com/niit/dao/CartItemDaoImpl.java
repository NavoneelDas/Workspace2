package com.niit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.CartItem;
import com.niit.model.CustomerOrder;
import com.niit.model.User;
@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao {
	@Autowired
private SessionFactory sessionFactory;

	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		return user;
	}

	public void saveOrUpdateCartItem(CartItem cartItem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);

	}

	public List<CartItem> getCartItems(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from CartItem where user.email=?");
		query.setString(0, email);
		List<CartItem> cartItems=query.list();
		return cartItems;
	
		
	}

	public void removeCartItem(int itemId) {
		Session session=sessionFactory.getCurrentSession();
		CartItem cartItem=(CartItem)session.get(CartItem.class, itemId);
		session.delete(cartItem);

	}
	
	
		public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
			Session session=sessionFactory.getCurrentSession();
			session.save(customerOrder);
			return customerOrder;
		}

}
