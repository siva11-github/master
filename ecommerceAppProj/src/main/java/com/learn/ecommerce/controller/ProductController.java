package com.learn.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dto.ProductCategoryDto;
import com.learn.ecommerce.dto.ProductDto;
import com.learn.ecommerce.entity.Product;
import com.learn.ecommerce.entity.ProductCategory;
import com.learn.ecommerce.exception.ResourceNotFoundException;
import com.learn.ecommerce.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	public List<ProductDto> getAllProducts() {
		return productService.getAllProduct().stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity < ProductDto > getProductById(@PathVariable long id) {
		Product productResponse=productService.getProductById(id);
		if(productResponse==null)
		{
			throw new ResourceNotFoundException("Prodouct not Exists ");
		}
		ProductDto product=modelMapper.map(productResponse,ProductDto.class);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity <ProductDto> createProduct(@RequestBody ProductDto productDto) {

		Product productRequest =modelMapper.map(productDto, Product.class);
		Product productResponse =productService.createProduct(productRequest);
		ProductDto product=modelMapper.map(productResponse,ProductDto.class);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/productCategory")
	public ResponseEntity <ProductCategoryDto > createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
		
		ProductCategory productcatRequest =modelMapper.map(productCategoryDto, ProductCategory.class);
		ProductCategory productcatesponse =productService.createProductsList(productcatRequest);
		ProductCategoryDto productcatdto=modelMapper.map(productcatesponse,ProductCategoryDto.class);
		return new ResponseEntity<>(productcatdto, HttpStatus.OK);
	}

	@GetMapping("/{pcName}")
	public ResponseEntity < ProductCategoryDto > getProducts(@RequestParam("categoryName") String name) {
		ProductCategory productcatesponse =productService.getProductsByCategory(name);
		if(productcatesponse==null)
		{
			throw new ResourceNotFoundException("Prodouct Category not Exists ");
		}
		ProductCategoryDto productcatdto=modelMapper.map(productcatesponse,ProductCategoryDto.class);
		return new ResponseEntity<>(productcatdto, HttpStatus.OK);
	}
	
	@GetMapping("/{pcId}")
	public ResponseEntity < ProductCategoryDto > getProducts(@RequestParam("categoryId") long id) {
		Optional<ProductCategory> productcatesponse =productService.getProductsById(id);
		if(!productcatesponse.isPresent())
		{
			throw new ResourceNotFoundException("Prodouct Category not Exists ");
		}
		ProductCategory productCategory=productcatesponse.get();
		ProductCategoryDto productcatdto=modelMapper.map(productCategory,ProductCategoryDto.class);
		return new ResponseEntity<>(productcatdto, HttpStatus.OK);
	}

	@PutMapping("/products")
	public ResponseEntity < ProductDto > updateProduct(@RequestBody ProductDto productDto){
		Product productRequest =modelMapper.map(productDto, Product.class);
		Product productResponse =productService.updateProduct(productRequest);
		ProductDto product=modelMapper.map(productResponse,ProductDto.class);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity <String> deleteProduct(@PathVariable long id) 
	{
		String response=productService.deleteProduct(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
