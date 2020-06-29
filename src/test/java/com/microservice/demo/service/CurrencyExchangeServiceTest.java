package com.microservice.demo.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.microservice.demo.domain.ExchangeValueDto;
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
	
	@Test
	void testCreateExchangeValue() {
		ExchangeValueDto exchangeValueDto = new ExchangeValueDto();
		exchangeValueDto.setCurrencyFrom("USD");
		exchangeValueDto.setCurrencyTo("INR");
		exchangeValueDto.setExchangeRate(BigDecimal.valueOf(74.65));
		
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setFrom("USD");
		exchangeValue.setTo("INR");
		exchangeValue.setConversionMultiple(BigDecimal.valueOf(74.65));
		exchangeValue.setId(10002L);
		when(exchangeValueRepository.save(Mockito.any())).thenReturn(exchangeValue);
		
		currencyExchangeService.createExchangeValue(exchangeValueDto);
		
		verify(exchangeValueRepository, times(1)).save(Mockito.any());
	}
	
	@Test
	void testUpdateExchangeValue() {
		ExchangeValueDto exchangeValueDto = new ExchangeValueDto();
		exchangeValueDto.setCurrencyFrom("USD");
		exchangeValueDto.setCurrencyTo("INR");
		exchangeValueDto.setExchangeRate(BigDecimal.valueOf(72.50));
		exchangeValueDto.setCurrencyExchangeId(10002L);
		
		
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setFrom("USD");
		exchangeValue.setTo("INR");
		exchangeValue.setConversionMultiple(BigDecimal.valueOf(72.50));
		exchangeValue.setId(10002L);
		when(exchangeValueRepository.save(Mockito.any())).thenReturn(exchangeValue);
		
		currencyExchangeService.updateExchangeValue(exchangeValueDto);
		verify(exchangeValueRepository, times(1)).save(Mockito.any());
	}
	
	@Test
	void testDeleteExchangeValue() {
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setFrom("USD");
		exchangeValue.setTo("INR");
		exchangeValue.setConversionMultiple(BigDecimal.valueOf(72.50));
		exchangeValue.setId(10002L);
		
		when(exchangeValueRepository.findByFromAndTo(Mockito.anyString(), Mockito.anyString())).thenReturn(exchangeValue);
		
		doNothing().when(exchangeValueRepository).delete(Mockito.any());
		
		currencyExchangeService.deleteExchangeValue("USD", "INR");
		verify(exchangeValueRepository, times(1)).delete(Mockito.any());
	}
	
	@Test
	void testGetAllExchangeValue() {
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
		
		when(exchangeValueRepository.findAll()).thenReturn(listExchangeValues);
		
		currencyExchangeService.getAllExchangeValue();
		
		Assertions.assertNotNull(listExchangeValues);
		verify(exchangeValueRepository, times(1)).findAll();
	}

}
