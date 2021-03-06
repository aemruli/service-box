package org.springframework.cloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Agim Emruli
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ExchangeService {

    public static void main(String[] args) {
        new SpringApplication(ExchangeService.class).run(args);
    }
}
