package com.recountmedia.webcrawler.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ScrapperConstants {

    public static final String SCRAPPER_BASE_URL = "scrapper.base_url";

    public static String SCRAPPER_PHONE_REGEX = "scrapper.phone_num_regex";

    public static String SCRAPPER_MAX_SCRAP_DEPTH = "scrapper.max_scrap_depth";

    private static Map<String, String> properties = new HashMap<String, String>();

    public static String get(String key) {
        return properties.get(key);
    }

    public static void setDefaultProperties(){
        if(!properties.containsKey(SCRAPPER_BASE_URL) || properties.get(SCRAPPER_BASE_URL).equals("") ){
            properties.put(SCRAPPER_BASE_URL, "https://jayesh2011.github.io/script-crawler/home.html");
        }
        if(!properties.containsKey(SCRAPPER_PHONE_REGEX) || properties.get(SCRAPPER_PHONE_REGEX).equals("") ){
            properties.put(SCRAPPER_PHONE_REGEX, "(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}");
        }
        if(!properties.containsKey(SCRAPPER_MAX_SCRAP_DEPTH) || properties.get(SCRAPPER_MAX_SCRAP_DEPTH).equals("") ){
            properties.put("scrapper.max_scrap_depth", "5");
        }
    }

    public static void setProperties() throws IOException {
        Properties propertyFile = new Properties();
        propertyFile.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

        if(propertyFile.size() == 0) {
            System.out.println("application.properties file not found");
            System.exit(0);
        } else {
            System.out.println("Setting the parameters from application.properties file : " + propertyFile.size());
            for (String oneKey : propertyFile.stringPropertyNames()) {
                properties.put(oneKey, propertyFile.getProperty(oneKey));
            }
        }

    }

}
