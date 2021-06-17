package com.learn.ecommerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dto.OrderItemDto;
import com.learn.ecommerce.entity.OrderItem;
import com.learn.ecommerce.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/order")
	public ResponseEntity<OrderItemDto> orderCreationFromCart(@RequestBody OrderItemDto orderItemDto)
	{
		OrderItem orderItemRequest =modelMapper.map(orderItemDto, OrderItem.class);
		OrderItem orderItemResponse  =orderService.createOrderFromCart(orderItemRequest);
		OrderItemDto orderItem=modelMapper.map(orderItemResponse,OrderItemDto.class);
		return new ResponseEntity<>(orderItem, HttpStatus.OK);
		
	}

}
