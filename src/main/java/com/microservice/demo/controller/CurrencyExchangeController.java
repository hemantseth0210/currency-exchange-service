package com.microservice.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.entity.ExchangeValue;
import com.microservice.demo.repository.ExchangeValueRepository;
import com.microservice.demo.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExchangeService currencyExchangeService;
	
	
	
	@GetMapping("/currency-exchange/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		return currencyExchangeService.getExchangeValue(from, to);
	}
}
