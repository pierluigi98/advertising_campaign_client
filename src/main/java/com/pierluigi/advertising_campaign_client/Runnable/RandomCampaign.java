package com.pierluigi.advertising_campaign_client.Runnable;

import com.pierluigi.advertising_campaign_client.Domain.Campaign;
import com.pierluigi.advertising_campaign_client.Domain.Counter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

public class RandomCampaign {
    private Random random = new Random();

    public RandomCampaign() {
    }

    public Campaign getCampaign()
    {
        String url ="http://localhost:8080/readAll";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("username","password");
        HttpEntity<Campaign> requestEntity = new HttpEntity<>(null,httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Campaign>>responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Campaign>>() {
                }
        );
        if(responseEntity.getBody().isEmpty()) return null;
        else {
            return responseEntity.getBody().get(random.nextInt(responseEntity.getBody().size()));
        }
    }

    public Counter.Categories getCategory() {
        return Counter.Categories.values()[random.nextInt(Counter.Categories.values().length)];
    }

    public int getIncrement() {
        return random.nextInt(100);
    }
}
