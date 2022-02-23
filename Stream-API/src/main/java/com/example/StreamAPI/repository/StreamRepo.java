package com.example.StreamAPI.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.StreamAPI.model.Product;

public interface StreamRepo extends JpaRepository<Product, Integer>{

	//@Query(value = "SELECT product_id, price, product_name,launch_date FROM product1 ORDER BY product_name ",nativeQuery= true)
	@Query("from Product")
	List<Product> sortByName();
	
	@Query("from Product where product_id =:product_id")
	List<Product> getById();

	//@Query(value ="select product_id, product_name, price,launch_date from product1 order by price ASC", nativeQuery = true)
	@Query("from Product p")
	List<Product> sortByPrice();

	//@Query(value ="select product_id, product_name, price,launch_date from product1 order by price ASC", nativeQuery = true)
	@Query("from Product p")
	Collection<Product> sortByPriceFromAndTo();
	
	//@Query(value ="select product_id, product_name, price,launch_date from product1 order by price DESC", nativeQuery = true)
	@Query("from Product p order by p.price DESC")
	Collection<Product> sortByPriceDesc();
	
	@Query("delete from Product "+" where id = product_id")
	public void deleteProduct(int Id);
	
	@Query("insert into Product(product_id,product_name,price,launch_date)"
			+"select product_id, product_name,price,launch_date from Product")
	List<Product> addProduct(Product product);
	
	@Query("update Product set product_name =: product_name, price = :price, launch_date =:launch_date  "+" where product_id = :product_id ")
	Product updateProduct( Product product);
}
