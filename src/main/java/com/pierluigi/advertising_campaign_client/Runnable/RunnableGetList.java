package com.pierluigi.advertising_campaign_client.Runnable;

import com.pierluigi.advertising_campaign_client.AdvertisingCampaignClientApplication;
import com.pierluigi.advertising_campaign_client.Domain.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class RunnableGetList implements Runnable{
    private int limit;
    private Logger logger =  LoggerFactory.getLogger(AdvertisingCampaignClientApplication.class);

    public RunnableGetList(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {

        String url = "http://localhost:8080/readWithLimit?limit="+this.limit;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBasicAuth("username","password");
        HttpEntity<Campaign> requestEntity = new HttpEntity<>(null,requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        try {
        ResponseEntity<List<Campaign>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<Campaign>>() { //solo per tipi annidati
                        });
        logger.info(response.getStatusCode().toString());

        if (response.getBody().isEmpty()) {
            logger.info("List empty");
        }
        else {
            logger.info(response.getBody().toString());
        } }
        catch (Exception exception) {
            logger.error("",exception);
        }
} }
