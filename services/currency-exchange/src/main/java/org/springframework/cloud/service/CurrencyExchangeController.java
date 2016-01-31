package org.springframework.cloud.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Agim Emruli
 */
@RestController
public class CurrencyExchangeController {

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/rate/{currency}/{value}")
    public BigDecimal exchange(@PathVariable String currency, @PathVariable BigDecimal value) throws InterruptedException {
        if (value.longValue() == 4200L) {
            Thread.sleep(10000);
        }

        JsonNode jsonNode = this.restTemplate.getForObject("http://api.fixer.io/latest", JsonNode.class);
        double rate = jsonNode.get("rates").findValue(currency).asDouble();
        return value.multiply(new BigDecimal(rate)).setScale(2, RoundingMode.HALF_UP);
    }
}
