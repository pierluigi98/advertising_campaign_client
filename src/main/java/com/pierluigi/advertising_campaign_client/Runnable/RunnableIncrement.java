package com.pierluigi.advertising_campaign_client.Runnable;

import com.pierluigi.advertising_campaign_client.AdvertisingCampaignClientApplication;
import com.pierluigi.advertising_campaign_client.Domain.Campaign;
import com.pierluigi.advertising_campaign_client.Domain.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RunnableIncrement implements Runnable{
    private String id;
    private Counter.Categories category;
    private int inc;
    private Logger logger =  LoggerFactory.getLogger(AdvertisingCampaignClientApplication.class);

    public RunnableIncrement(String id, Counter.Categories category, int inc) {
        this.id = id;
        this.category = category;
        this.inc = inc;
    }

    @Override
    public void run() {
        //Spring boot starter logging è incluso già in starter web
        //System.currentTimeMillis();

        String url = "http://10.172.65.33:8080/increment?id="+this.id+"&inc="+this.inc+"&cat="+this.category;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBasicAuth("username","password");
        HttpEntity<Campaign> requestEntity = new HttpEntity<>(null,requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Campaign> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            requestEntity,
                            Campaign.class
                    );
            logger.info(response.getStatusCode().toString());
            logger.info(response.getBody().toString());
        }
        catch (Exception exception) {
            logger.error("",exception);
        }

    }
}
