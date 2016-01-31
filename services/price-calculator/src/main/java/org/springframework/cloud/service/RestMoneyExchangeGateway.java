package org.springframework.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Agim Emruli
 */
@Service
public class RestMoneyExchangeGateway implements MoneyExchangeGateway {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    @HystrixCommand(commandProperties = @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value = "2000"))
    public Double exchangeMoney(String currency, long amount) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CURRENCY");
        if (instances.isEmpty()) {
            throw new IllegalStateException("No service available for query");
        }

        ServiceInstance serviceInstance = instances.get(0);
        return this.restTemplate.getForObject(serviceInstance.getUri().toASCIIString() +  "/rate/{currency}/{price}", Double.class, currency, amount);
    }
}
