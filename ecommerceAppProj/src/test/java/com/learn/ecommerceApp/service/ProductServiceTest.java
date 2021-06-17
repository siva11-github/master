package com.learn.ecommerceApp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.entity.Product;
import com.learn.ecommerce.entity.User;
import com.learn.ecommerce.repository.ProductRepository;
import com.learn.ecommerce.services.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productRepository;

	User user;
	
	@Before
	 public void setup(){
		
		productService=Mockito.mock(ProductService.class);
		productRepository=Mockito.mock(ProductRepository.class);
	 
	 }
	
	@Test
	public void getallproductTest()
	{
		List<Product> products =new ArrayList<Product>();
		Product p=new Product();
		p.setAvailability(true);
		p.setDescription("oneplus");
		p.setId(1);
		Product p2=new Product();
		p2.setAvailability(true);
		p2.setDescription("samsung");
		p2.setId(2);
		products.add(p2);
		products.add(p);
		when(productRepository.findAll()).thenReturn(products);
	}

	@Test
	public void updateProductTest()
	{
		Product p=new Product();
		p.setAvailability(true);
		p.setDescription("oneplus");
		p.setId(1);
		when(productRepository.save(p)).thenReturn(p);
		assertEquals("oneplus", p.getDescription());
	}

}
