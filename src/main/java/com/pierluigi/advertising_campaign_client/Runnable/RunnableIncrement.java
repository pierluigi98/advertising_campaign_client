package com.pierluigi.advertising_campaign_client.Runnable;

import com.pierluigi.advertising_campaign_client.AdvertisingCampaignClientApplication;
import com.pierluigi.advertising_campaign_client.Domain.Campaign;
import com.pierluigi.advertising_campaign_client.Domain.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RunnableIncrement implements Runnable{

    private Logger logger =  LoggerFactory.getLogger(AdvertisingCampaignClientApplication.class);

    public RunnableIncrement(){}

    @Override
    public void run() {
        RandomCampaignInterf randomCampaign = new RandomCampaign();

        String url = "http://localhost:8080/increment?id="+
                randomCampaign.getCampaign().getIdCampaign()+
                "&inc="+randomCampaign.getIncrement()
                +"&cat="+randomCampaign.getCategory();

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
            logger.info(response.getBody().toString());
        }
        catch (Exception exception) {
            logger.error("",exception);
        }

    }
}
