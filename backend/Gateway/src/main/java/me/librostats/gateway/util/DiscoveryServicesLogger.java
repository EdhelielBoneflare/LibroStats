package me.librostats.gateway.util;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscoveryServicesLogger {

    private static final Logger logger = LoggerFactory.getLogger(DiscoveryServicesLogger.class);

    @Bean
    ApplicationRunner logDiscoveryServices(DiscoveryClient discoveryClient) {
        return args -> {
            StringBuilder logData = new StringBuilder("Registered %s discovery services:\n"
                    .formatted(discoveryClient.getServices().size()));
            discoveryClient.getServices().forEach(service -> {
                discoveryClient.getInstances(service).forEach(instance -> {
                    logData.append("Service: %s - Instance: %s:%s (%s:%s)\n".formatted(
                            service,
                            instance.getServiceId(),
                            instance.getInstanceId(),
                            instance.getHost(),
                            instance.getPort()));
                });
            });
            logger.info(logData.toString());
        };
    }
}
