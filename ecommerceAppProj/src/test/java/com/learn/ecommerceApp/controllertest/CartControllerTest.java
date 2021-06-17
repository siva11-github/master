package com.learn.ecommerceApp.controllertest;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.controller.CartController;
import com.learn.ecommerce.entity.Cart;
import com.learn.ecommerce.entity.CartItem;
import com.learn.ecommerce.services.CartService;


@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class CartControllerTest {


	@InjectMocks
	private CartController cartController;

	@Mock
	private CartService cartService;

	@Test
	public void addProductTest()
	{
		Cart cart =new Cart();
		List<CartItem> cartitem=new ArrayList<CartItem>();
		CartItem ci=new CartItem();
		ci.setItemId(1);
		ci.setProductId(1);
		ci.setQuantity(1);
		cart.setCartId(1);
		cartitem.add(ci);
		cart.setItems(cartitem);
		when(cartService.addProduct(cart)).thenReturn("product(s) sucessfully added to cart");
		assertNotEquals("", cartService.addProduct(cart));
	}

	@Test
	public void updateCartTest()
	{
		Cart cart =new Cart();
		List<CartItem> cartitem=new ArrayList<CartItem>();
		CartItem ci=new CartItem();
		ci.setItemId(1);
		ci.setProductId(1);
		ci.setQuantity(1);
		cart.setCartId(1);
		cartitem.add(ci);
		cart.setItems(cartitem);
		when(cartService.cartUpdate(cart)).thenReturn("productId "+cart.getCartId()+" is not avaliable please try to add another product into the cart");
		assertNotEquals(1, cartService.cartUpdate(cart));

	}
}
