package org.springframework.cloud.sample.servicebox.infrastructure;

import com.netflix.turbine.discovery.InstanceDiscovery;
import com.netflix.turbine.init.TurbineInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.turbine.TurbineLifecycle;
import org.springframework.context.annotation.Bean;

/**
 * @author Agim Emruli
 */
@EnableTurbine
@SpringBootApplication
public class TurbineServer {

    public static void main(String[] args) {
        new SpringApplication(TurbineServer.class).run(args);
    }


    @Bean
    public TurbineLifecycle turbineLifecycle(InstanceDiscovery instanceDiscovery) {
        return new RunningAwareTurbineLifecycle(instanceDiscovery);
    }


    static class RunningAwareTurbineLifecycle extends TurbineLifecycle {

        public RunningAwareTurbineLifecycle(InstanceDiscovery instanceDiscovery) {
            super(instanceDiscovery);
        }

        private boolean running = false;

        @Override
        public void start() {
            super.start();
            running = true;
        }

        @Override
        public boolean isRunning() {
            return this.running;
        }

        @Override
        public void stop(Runnable callback) {
            stop();
            callback.run();
        }

        @Override
        public void stop() {
            TurbineInit.stop();
        }
    }
}
