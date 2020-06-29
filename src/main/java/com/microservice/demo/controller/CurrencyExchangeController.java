package com.microservice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.domain.ExchangeValueDto;
import com.microservice.demo.entity.ExchangeValue;
import com.microservice.demo.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExchangeService currencyExchangeService;
	
	@GetMapping("/currency-exchange/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		return currencyExchangeService.getExchangeValue(from, to);
	}
		
	@PostMapping("/currency-exchange/rate")
	public void createExchangeValue(@RequestBody ExchangeValueDto exchangeValueDto) {
		currencyExchangeService.createExchangeValue(exchangeValueDto);
	}
	
	@PutMapping("/currency-exchange/rate")
	public void updateExchangeValue(@RequestBody ExchangeValueDto exchangeValueDto) {
		currencyExchangeService.updateExchangeValue(exchangeValueDto);
	}
	
	@DeleteMapping("/currency-exchange/{from}/to/{to}")
	public void deleteExchangeValue(@PathVariable String from, @PathVariable String to) {
		currencyExchangeService.deleteExchangeValue(from, to);
	}
	
	@GetMapping("/currency-exchange/rates")
	public List<ExchangeValue> getAllExchangeValue() {
		return currencyExchangeService.getAllExchangeValue();
	}
}
