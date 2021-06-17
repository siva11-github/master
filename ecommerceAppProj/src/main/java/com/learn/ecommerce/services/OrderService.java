package com.learn.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.entity.Cart;
import com.learn.ecommerce.entity.CartItem;
import com.learn.ecommerce.entity.OrderItem;
import com.learn.ecommerce.entity.Product;
import com.learn.ecommerce.entity.User;
import com.learn.ecommerce.feignclient.FundsTransferInterface;
import com.learn.ecommerce.repository.CartRepository;
import com.learn.ecommerce.repository.OrderRepository;
import com.learn.ecommerce.repository.ProductRepository;
import com.learn.ecommerce.repository.UserRepository;

@Service
public class OrderService {


	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FundsTransferInterface fundsTransferInterface;

	public OrderItem createOrderFromCart(OrderItem orderItem) {

		double prodcutPrice = 0;
		Optional<Cart> cart=cartRepository.findById(orderItem.getCartId());
		if(cart.isPresent()) {	
		Cart ct=cart.get();
		orderItem.setCart(ct);
		List<CartItem> cartitem=ct.getItems();
		for(CartItem ci:cartitem)
		{
			Optional<Product> product=productRepository.findById(ci.getProductId());
			if(product.isPresent())
			{
				Product p=product.get();
				prodcutPrice=prodcutPrice+p.getPrice()*ci.getQuantity();
			}
		}
		orderItem.setOrderPrice(prodcutPrice);
		orderItem.setPaymentStatus("Pending");
		return orderRepository.save(orderItem);
		}
		else
			return orderItem;
	}

	public String paymentSection(int orderId, int userid) {
		Optional<OrderItem> orderDetail=orderRepository.findById(orderId);
		Optional<User> userDetails=userRepository.findById(userid);
		if(!orderDetail.isPresent())
			return "Entered OrderId not Exists";
		if(!userDetails.isPresent())
			return "Entered UserId not Exists";
		String paymentStatus=fundsTransferInterface.transferAmount(userDetails.get().getEmail(),"ecommercesales@gmail.com",orderDetail.get().getOrderPrice());
		OrderItem order=orderDetail.get();
		if("Transaction Sucessfull".equalsIgnoreCase(paymentStatus))
		{
			order.setPaymentStatus("Completed");
		}
		order.setUserId(userid);
		order.setPaymentStatus("Not Completed");
		orderRepository.save(order);
		return paymentStatus;
	}

}
