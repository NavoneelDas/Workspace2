package com.niit.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	@NotEmpty(message="productname cannot be blank")
	private String productname;
	@NotEmpty(message="Description is mandatory")
	private String description;
	@Min(value=10,message="Mininum price must be 10")
	private double price;
	@Min(value=0,message="Quantity cannot be less than 0")
	private int quantity;
	
	@ManyToOne
private Category category;
	@Transient
	private MultipartFile image;
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
	return "id " + this.id + " ProductName" + this.productname + " " + " Desc " + this.description + " Price " + this.price;
	}

}
