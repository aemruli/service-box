package org.springframework.cloud.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @author Agim Emruli
 */
@RestController
public class PriceCalculationController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final long RATE = 200;

    @RequestMapping("/price/{currency}/{hours}")
    public BigDecimal calculatePrice(@PathVariable String currency, @PathVariable long hours) {
        restTemplate.getRequestFactory();
        Double convertedPrice = this.restTemplate.getForObject("http://localhost:10000/rate/{currency}/{price}", Double.class, currency, hours * RATE);
        return new BigDecimal(convertedPrice).add(new BigDecimal(19)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
