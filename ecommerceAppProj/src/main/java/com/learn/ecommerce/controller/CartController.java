package com.learn.ecommerce.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dto.CartDto;
import com.learn.ecommerce.entity.Cart;
import com.learn.ecommerce.exception.ResourceNotFoundException;
import com.learn.ecommerce.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ModelMapper modelMapper;


	@PostMapping("/")
	public ResponseEntity<String> addProduct(@RequestBody CartDto cartdto) {
		Cart cartRequest=modelMapper.map(cartdto, Cart.class);
		String cartResponse=cartService.addProduct(cartRequest);
		return new ResponseEntity<>(cartResponse, HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity<CartDto> getCart(@RequestParam("cartId") long cartId) {
		Optional<Cart> cartResponse=cartService.getCartDetails(cartId);
		if(!cartResponse.isPresent())
			throw new ResourceNotFoundException("Cart "+cartId+"not exists");
		Cart cart=cartResponse.get();
		CartDto cartDto = modelMapper.map(cart, CartDto.class);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<String> updateCart(@RequestBody CartDto cartdto) {

		Cart cartRequest=modelMapper.map(cartdto, Cart.class);
		String cartResponse=cartService.cartUpdate(cartRequest);
		return new ResponseEntity<>(cartResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{itemId")
			public ResponseEntity<Object> deleteCartItem(@RequestParam("itemId") int itemid) {
				return ResponseEntity.ok().body(this.cartService.itemDeletion(itemid));
			}

			@DeleteMapping("/{id}")
			public ResponseEntity<Object> deleteAllCartItems(@RequestParam("cartId") long cartId) {
				Object cartResponse=cartService.cartDeletion(cartId);
				return new ResponseEntity<>(cartResponse, HttpStatus.OK);
			}
	
}
