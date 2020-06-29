package com.microservice.demo.domain;

import java.math.BigDecimal;

public class ExchangeValueDto {

	private Long currencyExchangeId;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal exchangeRate;
	public Long getCurrencyExchangeId() {
		return currencyExchangeId;
	}
	public void setCurrencyExchangeId(Long currencyExchangeId) {
		this.currencyExchangeId = currencyExchangeId;
	}
	public String getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public String getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
	
}
