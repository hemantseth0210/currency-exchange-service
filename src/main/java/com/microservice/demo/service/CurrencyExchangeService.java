package com.microservice.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.demo.domain.ExchangeValueDto;
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

	public void createExchangeValue(ExchangeValueDto exchangeValueDto) {
		ExchangeValue exchangeValue = new ExchangeValue(exchangeValueDto.getFrom(), exchangeValueDto.getTo(), exchangeValueDto.getConversionMultiple());
		logger.info("Creating the Exchange Value in MySql DB {}", exchangeValue);
		exchangeValueRepository.save(exchangeValue);
		logger.info("Created the Exchange Value in MySql DB {} ", exchangeValue);
	}

	public void updateExchangeValue(ExchangeValueDto exchangeValueDto) {
		ExchangeValue exchangeValue = new ExchangeValue(exchangeValueDto.getId(), exchangeValueDto.getFrom(), exchangeValueDto.getTo(), exchangeValueDto.getConversionMultiple());
		logger.info("Updating the Exchange Value in MySql DB {}", exchangeValue);
		exchangeValueRepository.save(exchangeValue);
		logger.info("Updated the Exchange Value in MySql DB {} ", exchangeValue);
	}

	public void deleteExchangeValue(String from, String to) {
		logger.info("Deleting Exchange Rate/Value for {} to {} ", from, to);
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		logger.info("Exchange Rate/Value found from MySql DB {} ", exchangeValue);
		exchangeValueRepository.delete(exchangeValue);
		logger.info("Deleted Exchange Rate/Value from MySql DB {} ", exchangeValue);
	}

	public List<ExchangeValue> getAllExchangeValue() {
		logger.info("Retrieving all the Exchange Value from MySql DB");
		List<ExchangeValue> exchangeValues = exchangeValueRepository.findAll();
		logger.info("Retrieved all the Exchange Value from MySql DB {}", exchangeValues);
		return exchangeValues;
	}

}
