package com.tim.productsandcatigories.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tim.productsandcatigories.models.Category;
import com.tim.productsandcatigories.models.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
	List<Product> findAll();
	List<Product> findByCategoriesNotContaining(Category category);
}
