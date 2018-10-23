package com.niit.controllers;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.dao.ProductDao;
import com.niit.model.Category;

@Controller
public class HomeController {
	@Autowired
private ProductDao productDao;
	@RequestMapping("/home")
	
	public String homePage(HttpSession session){
		session.setAttribute("categories",productDao.getAllCategories());

		return "Homepage";
	}
	@RequestMapping("/aboutus")
	public String aboutUs(){
		return "Aboutus";
	}
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	@RequestMapping("/loginerror")
	public String loginFailure(Model model){
		model.addAttribute("error","Invalid email/password..please enter valid email id");
		return "login";
	}
	@RequestMapping("/logout")
	public String logout(Model model){
		model.addAttribute("msg","Loggedout successfully..");
		return "login";
	}

}
