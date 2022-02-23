package com.example.StreamAPI.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.StreamAPI.model.Product;
import com.example.StreamAPI.repository.StreamRepo;

@Service
public class ServiceImpl implements Iservice {

	public StreamRepo repository;

	@Autowired
	public ServiceImpl(StreamRepo repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Product> sortByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Product> sortByName(String name) {
//		List<Product> sortByName = (List<Product>) repository.findAll().stream()
//				.sorted((e1, e2) -> e1.product_name.compareTo(e2.product_name)).collect(Collectors.toList());
//		System.out.println(sortByName.toString());
//
//		return repository.sortByName(null);
//	}

}
