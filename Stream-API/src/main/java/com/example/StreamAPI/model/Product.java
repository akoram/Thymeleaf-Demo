package com.example.StreamAPI.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product1")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int product_id;
	
	@Column(name= "product_name")
	public String product_name;
	
	@Column(name= "price")
	public double price;
	
	@Column(name= "launch_date")
	public Date launch_date;

	public Product() {}
	
	public Product(int product_id, String product_name, double price, Date launch_date) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.launch_date = launch_date;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getLaunch_date() {
		return launch_date;
	}

	public void setLaunch_date(Date launch_date) {
		this.launch_date = launch_date;
	}

//	@Override
//	public String toString() {
//		return "\n Product [launch_date=" + launch_date + "]";
//	}
	

//	@Override
//	public String toString() {
//		return "\n Product [product_name=" + product_name + "]";
//	}
	
	public String toString() {
		return "\n Product [Price=" + price + "]";
	}
	
	

//	@Override
//	public String toString() {
//		return "Product [product_id=" + product_id + ",\n product_name=" + product_name + ",\n price=" + price + "]";
//	}
	
	
	
}
