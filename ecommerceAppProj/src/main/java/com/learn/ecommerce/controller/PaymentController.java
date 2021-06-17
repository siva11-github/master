package com.learn.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.services.OrderService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	OrderService orderService;

	@PostMapping("/order")
	public String placeOrder(@RequestParam("orderId")  int orderId,@RequestParam("userId")  int userid)
	{
		return orderService.paymentSection(orderId,userid);

	}

}