package com.learn.sb.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.sb.onlinebanking.service.FundService;

@RestController
@RequestMapping("/funds")
public class TransferController {
	
	
	@Autowired
	FundService fundService;
	
	
	@PostMapping("/transfer")
	public String transferAmount(@RequestParam("fromEmail") String fromEmail,
												 @RequestParam("toEmail") String toEmail,
												 @RequestParam("balence") double balence) 
	{
		return fundService.transferAmount(fromEmail,toEmail,balence);
		
	}
	

}
