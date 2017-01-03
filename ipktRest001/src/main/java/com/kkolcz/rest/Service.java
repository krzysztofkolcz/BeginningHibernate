package com.kkolcz.rest;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClientException;

import com.kkolcz.types.UserData;

public class Service implements RestService {
    @SuppressWarnings("unused")
    private static Logger log = LogManager.getLogger(Service.class);
    private RestTemplate restTemplate;
    private String hostname = "localhost:8080";

    public void setRestTemplate(RestTemplate template) {
        this.restTemplate = template;
    }

    @Override
    public UserData getUserData(String id) {
        return restTemplate.getForObject("http://"+hostname+"/websystiqueRest002/user/"+id, UserData.class);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

