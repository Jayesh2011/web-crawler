# WEB CRAWLER

This project aims to scrap all the links recursively of the provided URL and prints all the phone numbers on the pages.

# Features

  - All required functionality
  - Parallel processing
  - Parameter to set depth value

# Requirements
  - JDK v8
  - maven v3.6.3
  - Intellij (preferred) - Just to analyse code

# How To Run the Project
-   Clone the source code 
-   Unzip the source file inside a any folder. 
-   Go inside that folder. Let's call it program_root
-   Check java version by java --version. It should be v1.8 or greater.
-   Check maven version by mvn --version. it should be v3.6.3 or greater.
-   Build the project by mvn clean install ( In Terminal, you should be inside program_root and
 pom.xml should be present there)
- Go to **target > webcrawler-deployment > config > application.properties file**. You can set all the URL, depth here.
- Go to **target > webcrawler-deployment > webcrawler.jar**. Run this jar file to get the output.


