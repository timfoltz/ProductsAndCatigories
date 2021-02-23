package com.tim.productsandcatigories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tim.productsandcatigories.models.Category;
import com.tim.productsandcatigories.models.Product;
import com.tim.productsandcatigories.services.CategoryService;
import com.tim.productsandcatigories.services.ProductService;

@Controller
public class MainController {
@Autowired
private ProductService productService;
@Autowired
private CategoryService categoryService;

@GetMapping("/")
private String index(Model model) {
	List<Category> cats = categoryService.allCategorys();
	List<Product> prods = productService.allProducts();
	System.out.println("in GET prods "+prods);
	model.addAttribute("prods",prods);
	model.addAttribute("cats",cats);
	return "index.jsp";
}
@GetMapping("/products/new")
private String newProduct(@ModelAttribute("product")Product product) {
	return "newProduct.jsp";
}
@PostMapping("/products")
private String createProduct(@Valid @ModelAttribute("product")Product product,
								BindingResult result) {
	if(result.hasErrors()) {
		return"newProduct.jsp";
	}else {
		productService.createProduct(product);
		List<Category> cats = product.getCategories();
		System.out.println("in POST cats "+cats);
		Long id = product.getId();
		return "redirect:/products/"+id;
	}
}
@GetMapping("/categories/new")
private String newCategory(@ModelAttribute("category")Category category) {
	return "newCategory.jsp";
}
@PostMapping("/categories")
private String createCategory(@Valid @ModelAttribute("category")Category category,
								BindingResult result) {
	if(result.hasErrors()) {
		return"newCategory.jsp";
	}else {
		categoryService.createCategory(category);
		List<Product> prods = category.getProducts();
		System.out.println("in POST prods "+prods);
		Long id = category.getId();
		return "redirect:/categories/"+id;
	}
}
	
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id")Long id,
							   
							  Model model) {
			Product prod = productService.findProduct(id);
			System.out.println("GET /products prod "+prod);
			List<Category> cats = prod.getCategories();
			System.out.println("GET /products cats "+cats);
			List<Category> categories = categoryService.findByProductsNotContaining(prod); 
			System.out.println("GET categories /products "+categories);
			model.addAttribute("product",prod);
			model.addAttribute("cats",cats);
			model.addAttribute("categories",categories);
			System.out.println("print only in GET producst "+prod.getCategories());
			return "oneProduct.jsp";
		}
	
@PutMapping("/products/{id}")
private String addCategoryToProduct(@PathVariable("id")Long id, 
									@RequestParam(value="categories")Long catId) {

		Product prod = productService.findProduct(id);
		productService.updateProduct(catId,prod);
		return "redirect:/products/"+id;
	}
@PutMapping("/categories/{id}")
private String addProductToCategory(@PathVariable("id")Long id, 
		@RequestParam(value="products")Long prodId) {
	
	Category cat = categoryService.findCategory(id);
	categoryService.updateCategory(prodId,cat);
	return "redirect:/categories/"+id;
}
@GetMapping("/categories/{id}")
public String showCategory(@PathVariable("id")Long id,
						   
						  Model model) {
		Category cat = categoryService.findCategory(id);

		List<Product> prods = cat.getProducts();

		List<Product> products = productService.findByProductsNotContaining(cat); 
		System.out.println(id);
		System.out.println(prods);
		System.out.println(products);
		model.addAttribute("category",cat);
		model.addAttribute("prods",prods);
		model.addAttribute("products",products);

		return "oneCategory.jsp";
	}



}
