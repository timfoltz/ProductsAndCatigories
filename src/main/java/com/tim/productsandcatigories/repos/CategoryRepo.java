package com.tim.productsandcatigories.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tim.productsandcatigories.models.Category;
import com.tim.productsandcatigories.models.Product;

public interface CategoryRepo extends CrudRepository<Category, Long> {
	List<Category> findAll();
	List<Category> findByProductsNotContaining(Product product);
}
