package com.recountmedia.webcrawler.code;

import java.io.IOException;
import java.util.*;

public class ScrapperService {

    public static List<String> allPhoneNumbers = new ArrayList<>();

    public static Queue<String> allLinks = new ArrayDeque<>();

    public static Set<String> allVisitedLinks = new HashSet<>();

    public void startScrapper() throws IOException, InterruptedException {

        allVisitedLinks.add(ScrapperConstants.get(ScrapperConstants.SCRAPPER_BASE_URL));
        ScrapperHelper.parseLink(ScrapperConstants.get(ScrapperConstants.SCRAPPER_BASE_URL), allLinks, allVisitedLinks, allPhoneNumbers);

        int depth = 0;
        while(!allLinks.isEmpty()){
            if( depth < Integer.parseInt(ScrapperConstants.get(ScrapperConstants.SCRAPPER_MAX_SCRAP_DEPTH)) ){
                int size = allLinks.size();
                for(int i = 0 ; i < size; i++ ){
                    String currLink = allLinks.poll();
                    allVisitedLinks.add(currLink);
                    ScrapperHelper.parseLink(currLink, allLinks, allVisitedLinks, allPhoneNumbers);
                }
            }else{
                break;
            }
            depth++;
        }

        System.out.println(allPhoneNumbers.toString());
    }

}
