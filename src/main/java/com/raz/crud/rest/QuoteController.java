package com.raz.crud.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Validated
public class QuoteController {

    private RestTemplate restTemplate;

    public QuoteController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("quote")
    public ResponseEntity<String> getQuote(@NotNull  @Size(min = 4, max = 10) @RequestParam(value = "category", required = false) String category) throws URISyntaxException {
        return restTemplate.getForEntity(new URI("https://quotes.rest/qod?category="+category), String.class);
    }
}
