package com.learn.sb.onlinebanking.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learn.sb.onlinebanking.entity.TransferFundsRequest;
import com.learn.sb.onlinebanking.service.TransferFundsService;

@RestController
@RequestMapping("/amount")
public class FundsController {
	
	
	@Autowired
	TransferFundsService transferFundsService;
	
	
	@PostMapping("/transfer")
	public ResponseEntity<Object> transferAmount(@Valid @RequestBody TransferFundsRequest details) 
	{
		String userDetails=transferFundsService.transferAmount(details);
		
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
	

}
