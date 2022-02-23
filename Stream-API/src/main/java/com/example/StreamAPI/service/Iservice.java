package com.example.StreamAPI.service;

import java.util.List;

import com.example.StreamAPI.model.Product;

public interface Iservice {
	
	public List<Product> findAll();
	
	public List<Product> sortByName(String name);

}
