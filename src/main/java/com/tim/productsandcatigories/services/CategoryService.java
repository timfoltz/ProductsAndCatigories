package com.tim.productsandcatigories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.productsandcatigories.models.Category;
import com.tim.productsandcatigories.models.Product;
import com.tim.productsandcatigories.repos.CategoryRepo;


@Service
public class CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductService productService;
//	******Create******
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}

	
//	******Read ONE******
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		return optionalCategory !=null ? optionalCategory.get() : null;
	}
	public List<Category> findByProductsNotContaining(Product product){
		return categoryRepo.findByProductsNotContaining(product);
	}
	
//	******Read ALL******
	public List<Category> allCategorys()	{
		return categoryRepo.findAll();
	}
//	******Update******
//	public void updateCategory(Category category) {
//		categoryRepo.save(category);
//	}
	public void updateCategory(Long prodId, Category category) {
		List<Product> prods = category.getProducts();
		Product newProd = productService.findProduct(prodId);
		prods.add(newProd);
		categoryRepo.save(category);
	}
//	******Destroy/Delete******
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}
}
