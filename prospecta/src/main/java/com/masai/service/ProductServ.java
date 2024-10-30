package com.masai.service;

import java.util.List;

import com.masai.exception.Addproduct;
import com.masai.exception.CatogoryNotFoundExcep;
import com.masai.model.Product;

public interface ProductServ {
	List<Product> getProductsByCategory(String category) throws CatogoryNotFoundExcep;

	Product addProduct(Product product) throws Addproduct;

}
