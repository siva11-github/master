package com.learn.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.entity.Product;
import com.learn.ecommerce.entity.ProductCategory;
import com.learn.ecommerce.exception.ResourceNotFoundException;
import com.learn.ecommerce.repository.ProductCategoryRepository;
import com.learn.ecommerce.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;


	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public ProductCategory createProductsList(ProductCategory listOfProducts) {
		List<Product> products=listOfProducts.getProducts();
		for(Product p:products)
		{
			productRepository.save(p);
		}
		return productCategoryRepository.save(listOfProducts);
	}

	public Product updateProduct(Product product) {
		Optional < Product > productDb = this.productRepository.findById(product.getId());

		if (productDb.isPresent()) {
			Product productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productUpdate.setDescription(product.getDescription());
			productRepository.save(productUpdate);
			return productUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}
	}

	public List < Product > getAllProduct() {
		return this.productRepository.findAll();
	}

	public Product getProductById(long productId) {

		Optional < Product > productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			throw new ResourceNotFoundException("Product not found with id : " + productId);
		}
	}

	public String deleteProduct(long productId) {
		Optional < Product > productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Product not found with id : " + productId);
		}
		return "product " +productId+" deleted sucessfully";
	}

	public ProductCategory getProductsByCategory(String name) {
		return productCategoryRepository.findByCategoryName(name);

	}

	public Optional<Product> getProduct(long productId) {
		return productRepository.findById(productId);
	}

	public Optional<ProductCategory> getProductsById(long id) {
		return productCategoryRepository.findById(id);
	}

}
