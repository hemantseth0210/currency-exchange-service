package com.microservice.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.microservice.demo.entity.ExchangeValue;
import com.microservice.demo.repository.ExchangeValueRepository;

@SpringBootTest
class CurrencyExchangeServiceTest {

	@Autowired
	CurrencyExchangeService currencyExchangeService;
	
	@MockBean
	ExchangeValueRepository exchangeValueRepository;
	
	@Test
	void testGetExchangeValue() {
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setFrom("USD");
		exchangeValue.setTo("INR");
		exchangeValue.setConversionMultiple(BigDecimal.valueOf(74.65));
		exchangeValue.setId(10002L);
		
		when(exchangeValueRepository.findByFromAndTo(Mockito.anyString(), Mockito.anyString())).thenReturn(exchangeValue);
		
		ExchangeValue exchangeValueReturned = currencyExchangeService.getExchangeValue("USD", "INR");
		Assertions.assertNotNull(exchangeValueReturned);
		verify(exchangeValueRepository, times(1)).findByFromAndTo(Mockito.anyString(), Mockito.anyString());
	}

}
