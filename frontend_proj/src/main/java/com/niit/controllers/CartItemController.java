package com.niit.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartItemDao;
import com.niit.dao.ProductDao;
import com.niit.model.CartItem;
import com.niit.model.Customer;
import com.niit.model.CustomerOrder;
import com.niit.model.Product;
import com.niit.model.ShippingAddress;
import com.niit.model.User;

@Controller
public class CartItemController {
	 @Autowired
		private ProductDao productDao;
		@Autowired
	   private CartItemDao cartItemDao;
	@RequestMapping(value="/cart/addtocart/{productId}")
	public String addToCart(@PathVariable int productId,@RequestParam int requestedQuantity,@AuthenticationPrincipal  Principal principal ){
		String email=principal.getName();//Email id of the logged in user
		List<CartItem> cartItems=cartItemDao.getCartItems(email);
		Product product=productDao.getProduct(productId);
		for(CartItem cartItem:cartItems){
			if(cartItem.getProduct().getId()==productId){
				cartItem.setQuantity(requestedQuantity);
				cartItem.setTotalPrice(requestedQuantity * product.getPrice() );
				cartItemDao.saveOrUpdateCartItem(cartItem);//update
				return "redirect:/cart/purchasedetails";
			}
		}
		
		
		CartItem cartItem=new CartItem();
		
		User user=cartItemDao.getUser(email);
		cartItem.setProduct(product);
		cartItem.setQuantity(requestedQuantity);
		cartItem.setUser(user);
		cartItem.setTotalPrice(requestedQuantity * product.getPrice());
		cartItemDao.saveOrUpdateCartItem(cartItem);
		return "redirect:/cart/purchasedetails";
	}
	@RequestMapping(value="/cart/purchasedetails")
	public String getPurchaseDetails(@AuthenticationPrincipal Principal principal,Model model,HttpSession session){
		String email=principal.getName();
		List<CartItem> cartItems=cartItemDao.getCartItems(email);
		model.addAttribute("cartItems",cartItems);
		session.setAttribute("cartSize",cartItems.size() );
		return "cart";
	}
	@RequestMapping(value="/cart/deletecartitem/{itemId}")
	public String removeCartItem(@PathVariable int itemId){
		cartItemDao.removeCartItem(itemId);
		return "redirect:/cart/purchasedetails";
	}
	@RequestMapping(value="/cart/clearcart")
	public String clearCart(@AuthenticationPrincipal Principal principal){
		String email=principal.getName();
		List<CartItem> cartItems=cartItemDao.getCartItems(email);
		for(CartItem cartItem:cartItems){
			cartItemDao.removeCartItem(cartItem.getItemId());
		}
		return "redirect:/cart/purchasedetails";
	}
	@RequestMapping(value="/cart/shippingaddress")
	public String getshippingaddress(@AuthenticationPrincipal Principal principal,Model model){
		String email=principal.getName();
		User user= cartItemDao.getUser(email);
		Customer customer=user.getCustomer();
		ShippingAddress shippingAddress=customer.getShippingaddress();
		model.addAttribute("shippingaddress",shippingAddress);
		return "shippingaddress";
	}
	@RequestMapping(value="/cart/createorder")
	public String createOrder(@ModelAttribute ShippingAddress shippingaddress,@AuthenticationPrincipal Principal principal,Model model,HttpSession session){
		String email=principal.getName();
		User user=cartItemDao.getUser(email);
		Customer customer=user.getCustomer();
		customer.setShippingaddress(shippingaddress);
		user.setCustomer(customer);
		
		List<CartItem> cartItems=cartItemDao.getCartItems(email);
		for(CartItem cartItem:cartItems){
			Product product=cartItem.getProduct();
			if((product.getQuantity()-cartItem.getQuantity())<0){
				cartItemDao.removeCartItem(cartItem.getItemId());
				model.addAttribute("productNA",product);
				return "productnotavailable";
			}
		}
		
		
		
		
		double grandTotal=0;
		for(CartItem cartItem:cartItems){
			grandTotal=grandTotal+cartItem.getTotalPrice();
		}
		
		
		CustomerOrder customerOrder=new CustomerOrder();
		customerOrder.setPurchaseDate(new Date());
		customerOrder.setUser(user);
		customerOrder.setGrandTotal(grandTotal);
		if(cartItems.size()>0)
		 customerOrder=cartItemDao.createCustomerOrder(customerOrder);
		 
		 
		
		 
		for(CartItem cartItem:cartItems){
			Product product=cartItem.getProduct();
			product.setQuantity(product.getQuantity() - cartItem.getQuantity());
			productDao.saveOrUpdateProduct(product );
			cartItemDao.removeCartItem(cartItem.getItemId());
		}
		model.addAttribute("customerorder",customerOrder);
		model.addAttribute("cartItems",cartItems);
		session.setAttribute("cartSize", 0);
		return "orderdetails";
	}

}
