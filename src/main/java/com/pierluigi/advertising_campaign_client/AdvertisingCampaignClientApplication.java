package com.pierluigi.advertising_campaign_client;

import com.pierluigi.advertising_campaign_client.Domain.Counter;
import com.pierluigi.advertising_campaign_client.Runnable.RunnableGetList;
import com.pierluigi.advertising_campaign_client.Runnable.RunnableIncrement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AdvertisingCampaignClientApplication implements CommandLineRunner {

	public static void main(String[] args)
	{
		SpringApplication.run(AdvertisingCampaignClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		RunnableGetList runnableGetList = new RunnableGetList(2);
		RunnableIncrement runnableIncrement = new RunnableIncrement("1", Counter.Categories.LISTEN_WEB,10);
		scheduledExecutorService.scheduleAtFixedRate(runnableIncrement,0,7,TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(runnableGetList,0,5, TimeUnit.SECONDS);

	}
}
