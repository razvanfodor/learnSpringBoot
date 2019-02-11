package com.raz.crud.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class QuoteController {

    private RestTemplate restTemplate;

    public QuoteController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("quote")
    public ResponseEntity<String> getQuote() throws URISyntaxException {
        return restTemplate.getForEntity(new URI("https://quotes.rest/qod?category=inspire"), String.class);
    }
}
