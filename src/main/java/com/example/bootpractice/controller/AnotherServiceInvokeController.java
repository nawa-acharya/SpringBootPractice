package com.example.bootpractice.controller;

import com.example.bootpractice.model.BackendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nawa
 * on 9/24/20.
 * (c)Marathon Computer Systems
 */

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "greeting")
public class AnotherServiceInvokeController {
    private String saying;
    private String backendServiceHost;
    private int backendServicePort;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/greeting2", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greeting() {
        return String.format("http://%s:%d/hello?greeting={greeting}", backendServiceHost, backendServicePort);
    }

    @GetMapping(value = "/greeting", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greeting2() {
        String backendServiceURL = String.format("http://%s:%d/api/backend?greeting={greeting}", backendServiceHost, backendServicePort);

        BackendDTO response = restTemplate.getForObject(backendServiceURL, BackendDTO.class, saying);

        return response.getGreeting() + " at host: " + response.getIp();
    }

    //getters and setters
    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getBackendServiceHost() {
        return backendServiceHost;
    }

    public void setBackendServiceHost(String backendServiceHost) {
        this.backendServiceHost = backendServiceHost;
    }

    public int getBackendServicePort() {
        return backendServicePort;
    }

    public void setBackendServicePort(int backendServicePort) {
        this.backendServicePort = backendServicePort;
    }
}
