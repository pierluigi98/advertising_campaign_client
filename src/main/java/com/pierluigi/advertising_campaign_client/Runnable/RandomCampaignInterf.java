package com.pierluigi.advertising_campaign_client.Runnable;

import com.pierluigi.advertising_campaign_client.Domain.Campaign;
import com.pierluigi.advertising_campaign_client.Domain.Counter;

public interface RandomCampaignInterf {
    Campaign getCampaign();
    Counter.Categories getCategory();
    int getIncrement();
}
