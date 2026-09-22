package com.rapido.ride_server.Client;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.rapido.ride_server.Dto.CaptainDto;

@Component
public class CaptainClient {

    private final RestClient.Builder restClientBuilder;
    private final DiscoveryClient discoveryClient;

    public CaptainClient(RestClient.Builder restClientBuilder,
                         DiscoveryClient discoveryClient) {
        this.restClientBuilder = restClientBuilder;
        this.discoveryClient = discoveryClient;
    }

    // Get AVAILABLE captains
    public List<CaptainDto> getAvailableCaptains() {

        List<ServiceInstance> instances =
                discoveryClient.getInstances("CAPTAIN-SERVICE");

        if (instances.isEmpty()) {
            throw new RuntimeException("Captain Service is not available");
        }

        ServiceInstance instance = instances.get(0);

        String url = instance.getUri().toString();

        return restClientBuilder
                .baseUrl(url)
                .build()
                .get()
                .uri("/captains/available")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CaptainDto>>() {});
    }

    // Change captain status
    public String updateCaptainStatus(Long id, String status) {

        List<ServiceInstance> instances =
                discoveryClient.getInstances("CAPTAIN-SERVICE");

        if (instances.isEmpty()) {
            throw new RuntimeException("Captain Service is not available");
        }

        ServiceInstance instance = instances.get(0);

        String url = instance.getUri().toString();

        return restClientBuilder
                .baseUrl(url)
                .build()
                .put()
                .uri("/captains/{id}/status?status={status}", id, status)
                .retrieve()
                .body(String.class);
    }
}