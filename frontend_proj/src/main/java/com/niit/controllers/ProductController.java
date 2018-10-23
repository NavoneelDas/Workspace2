package com.niit.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.ProductDao;
import com.niit.model.Product;

@Controller
public class ProductController {
	 @Autowired
		private ProductDao productDao;
		
		
		
		@RequestMapping(value="/all/getallproducts")
		public ModelAndView getAllProducts(){
			List<Product> products=productDao.getAllProducts();
			
			return new ModelAndView("productlist","productsAttr",products);
		}
		@RequestMapping(value="/all/getproduct/{id}")
		public ModelAndView getProduct(@PathVariable int id){
			Product product=productDao.getProduct(id);
			
			return new ModelAndView("productindetail","productAttr",product);
		}
		@RequestMapping(value="/admin/deleteproduct/{id}")
		public String deleteProduct(@PathVariable int id,HttpServletRequest request){
			productDao.deleteProduct(id);
			String rootContext=request.getServletContext().getRealPath("/");
			Path path=Paths.get(rootContext + "/WEB-INF/resources/images/"+ id+".png");
			if(Files.exists(path)){
				try {
					Files.delete(path);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			return "redirect:/all/getallproducts";
		}
		
		@RequestMapping(value="/admin/getproductform")
		public String getproductform(Model model){
			
			model.addAttribute("product",new Product());
			model.addAttribute("categories",productDao.getAllCategories());
			return "productform";
		}
		
		@RequestMapping("/admin/updateproductform/{id}")
		public String getUpdateProductForm(@PathVariable int id,Model model){
			Product product=productDao.getProduct(id);
			model.addAttribute("product",product);
			model.addAttribute("categories",productDao.getAllCategories());
			return "updateproductform";
		}
		
		
		@RequestMapping(value="/admin/saveorupdateproduct")
		public String saveOrUpdateProduct(@Valid @ModelAttribute(name="product") Product product,BindingResult result ,Model model,HttpServletRequest request){
			if(result.hasErrors()){
				model.addAttribute("categories",productDao.getAllCategories());
				if(product.getId()==0)
				return "productform";
				else
					return "updateproductform";
			}
			System.out.println("Product Id in SaveProduct method " + product.getId());
	        String rootContext= request.getServletContext().getRealPath("/");
	        
	        System.out.println(rootContext);
	        
			productDao.saveOrUpdateProduct(product);
			
			Path paths=Paths.get(rootContext + "/WEB-INF/resources/images/"+product.getId()+".png");
			MultipartFile productImage=product.getImage();
			if(productImage!=null && !productImage.isEmpty()){
				try {
					productImage.transferTo(new File(paths.toString()));
				} catch (IllegalStateException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			return "redirect:/all/getallproducts";
		}
		@RequestMapping(value="/all/searchByCategory")
		public String searchByCategory(@RequestParam String searchCondition,Model model)
		{
			if(searchCondition.equals("All"))
				model.addAttribute("searchCondition", "");
			else
				model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("productsAttr", productDao.getAllProducts());
			return "productlist";
		}
		

		
		
		

}
