package com.learn.sb.onlinebanking.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.learn.sb.onlinebanking.controller.FundsController;
import com.learn.sb.onlinebanking.entity.TransferFundsRequest;
import com.learn.sb.onlinebanking.service.TransferFundsService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FundsControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Mock
	@Autowired
	TransferFundsService transferFundsService;
	@InjectMocks
	@Autowired
	FundsController controller;


	@Before
	public void prepareMockMvc() {
		this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
		
		
		
	}

	@Test
	public void trasnferFundsWithOutFromAndToAccounts() throws Exception {
		TransferFundsRequest transferAccountRequest =new TransferFundsRequest();
		transferAccountRequest.setTranferAmt(10000);

		Mockito.when(transferFundsService.transferAmount(transferAccountRequest)).thenReturn("Source account number 0 does not exist.");
		this.mockMvc.perform(post("/funds/transfer").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"beneficiaryAccountNumber\": 0, \"sourceAccountNumber\": 0, \"tranferAmt\": 10000}")).andExpect(status().isBadRequest());
	}

	@Test
	public void tramsferFundsWithoutBalance() throws Exception {
		TransferFundsRequest transferAccountRequest =new TransferFundsRequest();
		transferAccountRequest.setSourceAccountNumber(1000l);
		transferAccountRequest.setBeneficiaryAccountNumber(1001l);
		transferAccountRequest.setTranferAmt(10000);
		Mockito.when(transferFundsService.transferAmount(transferAccountRequest)).thenReturn("Source account number 1000 does not exist.");
		this.mockMvc.perform(post("/funds/transfer").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"beneficiaryAccountNumber\": 1000, \"sourceAccountNumber\": 1001, \"tranferAmt\": 30000}")).andExpect(status().isBadRequest());
	}

	@Test
	public void transferFundsWithoutBalanceAndWithoutAccounts() throws Exception {
		TransferFundsRequest transferAccountRequest =new TransferFundsRequest();
		Mockito.when(transferFundsService.transferAmount(transferAccountRequest));
//		assertEquals("Source account number 0 does not exist.", str);
		this.mockMvc.perform(post("/funds/transfer").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void transferAmountWithNegativeBalance() throws Exception {
		TransferFundsRequest transferAccountRequest =new TransferFundsRequest();
		transferAccountRequest.setSourceAccountNumber(1000l);
		transferAccountRequest.setBeneficiaryAccountNumber(1001l);
		transferAccountRequest.setTranferAmt(-10000);
		Mockito.when(transferFundsService.transferAmount(transferAccountRequest)).thenReturn(null);
		this.mockMvc.perform(post("/funds/transfer").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"beneficiaryAccountNumber\": 1000, \"sourceAccountNumber\": 1001, \"tranferAmt\": -10000}")).andExpect(status().isBadRequest());
	}



	

}		
