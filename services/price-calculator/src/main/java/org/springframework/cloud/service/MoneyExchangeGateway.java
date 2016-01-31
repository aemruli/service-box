package org.springframework.cloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Agim Emruli
 */
@FeignClient("CURRENCY")
public interface MoneyExchangeGateway {

    @RequestMapping(value = "/rate/{currency}/{amount}", method = RequestMethod.GET)
    Double exchangeMoney(@PathVariable("currency") String currency, @PathVariable("amount") long amount);
}
