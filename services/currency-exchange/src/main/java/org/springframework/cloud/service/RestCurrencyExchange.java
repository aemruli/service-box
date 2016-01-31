package org.springframework.cloud.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Agim Emruli
 */
@Component
public class RestCurrencyExchange implements CurrencyExchange {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    @HystrixCommand
    public Double getExchangeRate(String currency) {
        JsonNode jsonNode = restTemplate.getForObject("http://api.fixer.io/latest", JsonNode.class);
        return jsonNode.get("rates").findValue(currency).asDouble();
    }
}
