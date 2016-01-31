package org.springframework.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Agim Emruli
 */
public interface CurrencyExchange {
    @HystrixCommand
    Double getExchangeRate(String currency);
}
