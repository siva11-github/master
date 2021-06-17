package com.learn.ecommerceApp.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.services.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest {
	
	@Mock
	OrderService orderService;
	
	@Test
	public void placeOrderTest()
	{
		when(orderService.paymentSection(1, 1)).thenReturn("Transaction was sucessfull");
		assertEquals(null, orderService.paymentSection(1, 0));
	}

}
