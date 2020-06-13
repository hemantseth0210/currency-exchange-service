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
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		//exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}",exchangeValue);
		return exchangeValue;
	}

}
