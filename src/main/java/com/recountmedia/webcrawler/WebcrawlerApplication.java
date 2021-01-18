package com.recountmedia.webcrawler;

import com.recountmedia.webcrawler.code.ScrapperConstants;
import com.recountmedia.webcrawler.code.ScrapperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebcrawlerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WebcrawlerApplication.class, args);
		ScrapperConstants.setProperties();
		ScrapperConstants.setDefaultProperties();
		ScrapperService scrapperService = new ScrapperService();
		try{
			scrapperService.startScrapper();
		}catch(InterruptedException e ){
			System.err.println("Exception to run program concurrently : " + e.getMessage() );
		}
	}

}
