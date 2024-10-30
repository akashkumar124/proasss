package com.masai.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.masai.exception.Addproduct;
import com.masai.exception.CatogoryNotFoundExcep;
import com.masai.model.Product;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServImpl implements ProductServ {

    private final RestTemplate restTemplate;

    public ProductServImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws CatogoryNotFoundExcep {
    	  String url = "https://fakestoreapi.com/products/category/" + category;
          try {
              Product[] products = restTemplate.getForObject(url, Product[].class);
              if (products == null || products.length == 0) {
                  throw new CatogoryNotFoundExcep("No products found in category: " + category);
              }
              return Arrays.asList(products);
          } catch (HttpClientErrorException.NotFound e) {
              throw new CatogoryNotFoundExcep("Category not found: " + category);
          } catch (Exception e) {
              throw new RuntimeException("Error fetching products from the external API", e);
          }
    }
    
    @Override
    public Product addProduct(Product product) throws Addproduct {
        String url = "https://fakestoreapi.com/products";
        HttpEntity<Product> request = new HttpEntity<>(product);
        
        try {
            ResponseEntity<Product> response = restTemplate.exchange(url, HttpMethod.POST, request, Product.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Specific handling for HTTP errors
            throw new Addproduct("Failed to create product: " + ex.getMessage());
        } catch (Exception ex) {
            // General error handling
            throw new RuntimeException("Unexpected error while creating product", ex);
        }
    }
}
