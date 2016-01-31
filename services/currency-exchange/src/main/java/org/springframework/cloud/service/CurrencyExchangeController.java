package org.springframework.cloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Agim Emruli
 */
@RestController
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchange currencyExchange;

    @RequestMapping("/rate/{currency}/{value}")
    public BigDecimal exchange(@PathVariable String currency, @PathVariable BigDecimal value) throws InterruptedException {
        LOGGER.trace("Received called with currency {} and value {}", currency, value);

        if (value.longValue() == 4200L) {
            Thread.sleep(10000);
        }

        double rate = currencyExchange.getExchangeRate(currency);
        return value.multiply(new BigDecimal(rate)).setScale(2, RoundingMode.HALF_UP);
    }

}
