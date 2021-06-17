package com.learn.ecommerceApp.controllertest;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.controller.ProductController;
import com.learn.ecommerce.entity.Product;
import com.learn.ecommerce.services.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {


	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	@Test
	public void getProdcut()
	{
		Product p=new Product();
		p.setAvailability(true);
		p.setDescription("oneplus");
		p.setId(1);
		when(productService.getProductById(p.getId())).thenReturn(p);
		assertEquals("oneplus", p.getDescription());
	}

	@Test
	public void getAllProdcutsTest()
	{ 
		List<Product> products =new ArrayList<Product>();
		Product p=new Product();
		p.setAvailability(true);
		p.setDescription("oneplus");
		p.setId(1);
		Product p2=new Product();
		p2.setAvailability(true);
		p2.setDescription("oneplus");
		p2.setId(2);
		products.add(p2);
		products.add(p);
		when(productService.getAllProduct()).thenReturn(products);
	}
	
	@Test
	public void updateProductTest()
	{
		Product p=new Product();
		p.setAvailability(true);
		p.setDescription("oneplus");
		p.setId(1);
		when(productService.updateProduct(p)).thenReturn(p);
		assertEquals("oneplus", p.getDescription());
	}

	@Test
	public void deleteProdcutTest()
	{
		Product p=new Product();
		p.setAvailability(true);
		p.setDescription("oneplus");
		p.setId(1);
		when(productService.deleteProduct(p.getId())).thenReturn(null);
		assertEquals(null, p.getId());
	}

}
