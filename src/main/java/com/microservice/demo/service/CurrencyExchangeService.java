package com.microservice.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.demo.controller.CurrencyExchangeController;
import com.microservice.demo.entity.ExchangeValue;
import com.microservice.demo.repository.ExchangeValueRepository;

@Service
public class CurrencyExchangeService {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);
	
	@Autowired
	ExchangeValueRepository exchangeValueRepository;
	
	public ExchangeValue getExchangeValue(String from, String to) {
		logger.info("Querying the MySql DB to find the exchange rates");
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		logger.info("Exchange Rate/Value found from MySql DB {} ", exchangeValue);
		return exchangeValue;
	}

}
