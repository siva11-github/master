package com.learn.ecommerce.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="http://localhost:6021/funds", value="fundsService")
public interface FundsTransferInterface {
	
	@PostMapping("/transfer")
	public String transferAmount(@RequestParam("fromEmail") String fromEmail,
												 @RequestParam("toEmail") String toEmail,
												 @RequestParam("balence") double balence);
	

}
