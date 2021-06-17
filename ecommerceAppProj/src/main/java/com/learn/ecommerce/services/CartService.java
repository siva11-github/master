package com.learn.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.entity.Cart;
import com.learn.ecommerce.entity.CartItem;
import com.learn.ecommerce.entity.Product;
import com.learn.ecommerce.repository.CartItemsRepository;
import com.learn.ecommerce.repository.CartRepository;


@Service
public class CartService
{

	@Autowired
	private ProductService productService;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemsRepository cartItemsRepository;


	public String createNewCart() {
		return UUID.randomUUID().toString();
	}

	public String addProduct(Cart cartItem) {

		boolean cartflag=false;
		long productId = 0;
		List<CartItem> cartItems=cartItem.getItems();
		for(int i=0;i<cartItems.size();i++)
		{
			CartItem ci=cartItems.get(i);
			Optional<Product> product=productService.getProduct(ci.getProductId());
			if(product.isPresent()) {
				cartItemsRepository.save(ci);
				cartRepository.save(cartItem);
				cartflag=true;
			}
			else {
				productId=ci.getProductId(); 
				cartflag=false;
			}

		}
		if(cartflag)
			return "product(s) sucessfully added to cart";
		else
			return "productId "+productId+" is not avaliable please try to add another product into the cart";

	}

	public String cartUpdate(Cart cart) {
		String info=addProduct(cart);
		if(info.contains("sucessfully"))
			return "Cart updated sucessfully";
		return info;

	}

	public String itemDeletion(int itemid) {
		if(cartItemsRepository.findById(itemid).isPresent()) {
			cartItemsRepository.deleteById(itemid);
			return "Itemid "+itemid+" deleted sucessfully";}
		else
			return "Selected Itemid "+itemid+" not avaliable in cart";

	}

	public Object cartDeletion(long cartId) {
		if(cartRepository.findById(cartId).isPresent()) {
			cartRepository.deleteById(cartId);
			return "cart "+cartId+" deleted sucessfully";}
		else
			return "Selected cart "+cartId+" not exists";
	}

	public Optional<Cart> getCartDetails(long itemid) {
		return cartRepository.findById(itemid);
	}
}
