package com.example.bootpractice.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Nawa
 * on 9/24/20.
 * (c)Marathon Computer Systems
 */

@RestController
@RequestMapping("/example")
@ConfigurationProperties(prefix = "helloapp")
public class PropertiesConfigController {
    private String saying;

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
    public String exampleForProperties() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return saying + " " + hostname;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }
}
