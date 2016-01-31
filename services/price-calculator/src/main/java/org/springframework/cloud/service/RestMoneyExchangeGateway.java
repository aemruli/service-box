package org.springframework.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Agim Emruli
 */
@Service
public class RestMoneyExchangeGateway implements MoneyExchangeGateway {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    @HystrixCommand(commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"))
    public Double exchangeMoney(String currency, long amount) {
        return loadBalancerClient.execute("CURRENCY", instance ->
                restTemplate.getForObject(instance.getUri().toASCIIString() + "/rate/{currency}/{price}",
                        Double.class, currency, amount));
    }
}
