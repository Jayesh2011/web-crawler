package com.recountmedia.webcrawler.code;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapperHelper {

    public static void parseLink(String linkUrl, Queue<String> allLinks, Set<String> allVisitedLinks, List<String> allPhoneNumbers ) throws IOException, InterruptedException {
        Document doc  = null;
        try{
            doc = Jsoup.connect(linkUrl).get();
        }catch(HttpStatusException e ){
            System.out.println("Unable to follow Link : " + linkUrl + "  Status : 404" );
            return;
        }

        Document finalDoc = doc;
        Thread thread1 = new Thread() {
            public void run() {
                addAllLinks(finalDoc, allLinks, allVisitedLinks);
            }
        };

        Document finalDoc1 = doc;
        Thread thread2 = new Thread() {
            public void run() {
                addAllPhoneNumbers(finalDoc1, allPhoneNumbers);
            }
        };

        // Start Both threads concurrently
        thread1.start();
        thread2.start();

        // Wait for both of the threads to finish
        thread1.join();
        thread2.join();
    }

    private static void addAllLinks(Document doc, Queue<String> allLinks, Set<String> allVisitedLinks ){
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String tempUrl = link.attr("abs:href");
            if(!allVisitedLinks.contains(tempUrl))
                allLinks.add(link.attr("abs:href"));
        }
    }

    private static void addAllPhoneNumbers(Document doc, List<String> allPhoneNumbers){
        String textContent = doc.text();
        Pattern pattern = Pattern.compile(ScrapperConstants.get(ScrapperConstants.SCRAPPER_PHONE_REGEX));
        Matcher m = Pattern.compile(ScrapperConstants.get(ScrapperConstants.SCRAPPER_PHONE_REGEX)).matcher(textContent);
        while (m.find()) {
            allPhoneNumbers.add(m.group());
        }
    }
}
