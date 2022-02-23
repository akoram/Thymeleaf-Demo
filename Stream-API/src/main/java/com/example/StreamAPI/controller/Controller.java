package com.example.StreamAPI.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StreamAPI.model.Product;
import com.example.StreamAPI.repository.StreamRepo;

@RestController
@RequestMapping("/api")
public class Controller {

	// public ServiceImpl service;
	@Autowired
	public StreamRepo repo;

//	@Autowired
//	public Controller(ServiceImpl service) {
//		this.service = service;
//	}

	// get All product List
	@GetMapping("all")
	public List<Product> getAll() {
		return repo.findAll();
	}

	// get BY Id product List
	@GetMapping("getById/{id}")
	public Optional<Product> getById(@PathVariable("id") int id) {
		return repo.findById(id);
	}

	// sort By product Name
	@GetMapping("sortName")
	public ResponseEntity<List<Product>> sortByName() {
		try {

			List<Product> sortByName = (List<Product>) repo.sortByName().stream()
					.sorted((e1, e2) -> e1.product_name.compareTo(e2.product_name)).collect(Collectors.toList());
			System.out.println(sortByName.toString());
			return new ResponseEntity<>(sortByName, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get price details asc order
	@GetMapping(value = { "/{sortPrice}", "/{sortpriceAsc}" })
	public ResponseEntity<List<Product>> sortByPrice() {
		try {

			List<Product> sortByPrice = (List<Product>) repo.sortByPrice().stream()
					.sorted(Comparator.comparingDouble(Product::getPrice)).collect(Collectors.toList());
			System.out.println(sortByPrice.toString());
			return new ResponseEntity<>(sortByPrice, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get specific price from and to
	@GetMapping("sortPrice/{price1}/{price2}")
	public ResponseEntity<List<Product>> sortByPriceTo(@PathVariable("price1") double price1,
			@PathVariable("price2") double price2) {
		try {

			List<Product> sortByPriceTo = (List<Product>) repo.sortByPriceFromAndTo().stream()
					.filter(e -> e.price > price1 && e.price < price2)
//					.sorted(Comparator.comparingDouble(Product :: getPrice))
					.collect(Collectors.toList());

			System.out.println(sortByPriceTo.toString());
			return new ResponseEntity<>(sortByPriceTo, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// get price details Desc order
	@GetMapping("sortPriceDesc")
	public ResponseEntity<List<Product>> sortByPriceDesc() {
		try {

			List<Product> sortByPriceDesc = (List<Product>) repo.sortByPriceDesc().stream()
					// .sorted(Comparator.comparingDouble(Product :: getPrice))
					.collect(Collectors.toList());
			System.out.println(sortByPriceDesc.toString());
			return new ResponseEntity<>(sortByPriceDesc, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// delete product
	@DeleteMapping("delete/{Id}")
	public String deleteProduct(@PathVariable int Id) {

		Optional<Product> products = repo.findById(Id);

		// throw exception if null

		if (products == null) {
			throw new RuntimeException("Product id not found - " + Id);
		}

		repo.deleteById(Id);

		return "Deleted Peoduct id - " + Id;
	}

	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		Product theProduct = repo.save(product);
		return theProduct;
	}
	//update
	  @PutMapping("/update/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
	        Optional<Product> productData = repo.findById(id);

	        if (productData.isPresent()) {
	            Product _product = productData.get();
	            _product.setProduct_name(product.getProduct_name());
	            _product.setPrice(product.getPrice());
	            _product.setLaunch_date(product.getLaunch_date()); 
	            return new ResponseEntity<>(repo.save(_product), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}