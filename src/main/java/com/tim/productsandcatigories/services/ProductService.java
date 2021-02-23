package com.tim.productsandcatigories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.productsandcatigories.models.Category;
import com.tim.productsandcatigories.models.Product;
import com.tim.productsandcatigories.repos.CategoryRepo;
import com.tim.productsandcatigories.repos.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryService categoryService;
	
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Product findProduct(Long id) {
		Optional<Product> product = productRepo.findById(id);
		return product !=null ? product.get() : null;
	}
	public List<Product> findByProductsNotContaining(Category category){
		return productRepo.findByCategoriesNotContaining(category);
	}
	//******Read ALL******
	public List<Product> allProducts()	{
		return productRepo.findAll();
	}
//	******Update******
	public void updateProduct(Long catId, Product product) {
		List<Category> cats = product.getCategories();
		System.out.println(cats);
		Category newCat = categoryService.findCategory(catId);
		cats.add(newCat);
		productRepo.save(product);
	}
//	******Destroy/Delete******
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}


}
