package com.microservice.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.demo.entity.ExchangeValue;
import com.microservice.demo.service.CurrencyExchangeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyExchangeControllerTest {

	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    CurrencyExchangeService currencyExchangeService;

    @Autowired
    ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	@Test
	public void testRetrieveExchangeValue() throws Exception {
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setFrom("USD");
		exchangeValue.setTo("INR");
		exchangeValue.setConversionMultiple(BigDecimal.valueOf(74.65));
		exchangeValue.setId(10002L);
		
		when(currencyExchangeService.getExchangeValue(Mockito.anyString(), Mockito.anyString())).thenReturn(exchangeValue);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		mockMvc.perform(get("/currency-exchange/USD/to/INR")).andExpect(status().isOk());
	}

}
