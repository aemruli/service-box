package org.springframework.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Agim Emruli
 */
@Service
public class RestMoneyExchangeGateway implements MoneyExchangeGateway {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"))
    public Double exchangeMoney(String currency, long amount) {
        return restTemplate.getForObject("http://CURRENCY/rate/{currency}/{price}",
                Double.class, currency, amount);
    }
}
