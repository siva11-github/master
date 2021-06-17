package com.learn.ecommerceApp.controllertest;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.controller.OrderController;
import com.learn.ecommerce.entity.OrderItem;
import com.learn.ecommerce.services.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {
	
	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderService;
	
	@Test
	public void orderCreationFromCartTest()
	{
		OrderItem oi=new OrderItem();
		oi.setOrderId(1);
		oi.setOrderPrice(1000);
		oi.setUserId(1);
		when(orderService.createOrderFromCart(oi)).thenReturn(oi);
		assertNotEquals(null, orderService.createOrderFromCart(oi));
	}

}
