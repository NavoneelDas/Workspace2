package com.niit.dao;

import java.util.List;

import com.niit.model.CartItem;
import com.niit.model.CustomerOrder;
import com.niit.model.User;

public interface CartItemDao {
	User getUser(String email);
	void saveOrUpdateCartItem(CartItem cartItem);
	List<CartItem> getCartItems(String email);
	void removeCartItem(int itemId);
	CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

}
