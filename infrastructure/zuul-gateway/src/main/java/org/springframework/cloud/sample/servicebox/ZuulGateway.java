package org.springframework.cloud.sample.servicebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Agim Emruli
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulGateway {

    public static void main(String[] args) {
        new SpringApplication(ZuulGateway.class).run(args);
    }
}
