package com.microservice.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.demo.domain.ExchangeValueDto;
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
	
	@Test
	public void testCreateExchangeValue() throws Exception {
		ExchangeValueDto exchangeValueDto = new ExchangeValueDto();
		exchangeValueDto.setCurrencyFrom("USD");
		exchangeValueDto.setCurrencyTo("INR");
		exchangeValueDto.setExchangeRate(BigDecimal.valueOf(72.50));
				
		doNothing().when(currencyExchangeService).createExchangeValue(Mockito.any());
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		mockMvc.perform(post("/currency-exchange/rate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(exchangeValueDto)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateExchangeValue() throws Exception {
		ExchangeValueDto exchangeValueDto = new ExchangeValueDto();
		exchangeValueDto.setCurrencyFrom("USD");
		exchangeValueDto.setCurrencyTo("INR");
		exchangeValueDto.setExchangeRate(BigDecimal.valueOf(72.50));
		exchangeValueDto.setCurrencyExchangeId(10002L);
		
		doNothing().when(currencyExchangeService).createExchangeValue(Mockito.any());
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		mockMvc.perform(put("/currency-exchange/rate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(exchangeValueDto)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteExchangeValue() throws Exception {
		doNothing().when(currencyExchangeService).createExchangeValue(Mockito.any());
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		mockMvc.perform(delete("/currency-exchange/USD/to/INR")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllExchangeValue() throws Exception {
		ExchangeValue exchangeValue1 = new ExchangeValue();
		exchangeValue1.setFrom("USD");
		exchangeValue1.setTo("INR");
		exchangeValue1.setConversionMultiple(BigDecimal.valueOf(74.65));
		exchangeValue1.setId(10001L);
		
		ExchangeValue exchangeValue2 = new ExchangeValue();
		exchangeValue2.setFrom("EUR");
		exchangeValue2.setTo("INR");
		exchangeValue2.setConversionMultiple(BigDecimal.valueOf(87.25));
		exchangeValue2.setId(10002L);
		
		List<ExchangeValue> listExchangeValues = new ArrayList<>();
		
		when(currencyExchangeService.getAllExchangeValue()).thenReturn(listExchangeValues);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		mockMvc.perform(get("/currency-exchange/rates")).andExpect(status().isOk());
	}

}
