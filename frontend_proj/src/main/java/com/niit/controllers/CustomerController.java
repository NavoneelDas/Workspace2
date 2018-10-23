package com.niit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CustomerDao;
import com.niit.model.Customer;

@Controller

public class CustomerController {
	@Autowired
private CustomerDao customerDao;
	@RequestMapping(value="/all/registrationform")
	public ModelAndView getRegistrationForm(){
		return new ModelAndView("registrationform","customer",new Customer());
	}
	@RequestMapping(value="/all/register")
	public String registerCustomer(@ModelAttribute(name="customer") Customer customer,Model model){
		if(!customerDao.isEmailUnique(customer.getUser().getEmail())){
			model.addAttribute("error","Email Id already Exists.. please enter different email address");
			return "registrationform";
		}
	   	customerDao.registerCustomer(customer);
	   	return "login";
	}

}
