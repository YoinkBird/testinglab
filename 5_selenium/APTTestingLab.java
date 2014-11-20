//package org.openqa.selenium.example;

import java.util.List;
import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.PageFactory;
// https://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/firefox/internal/ProfilesIni.html

// explanation http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
// design: see https://code.google.com/p/selenium/wiki/PageFactory
public class APTTestingLab {
    public static void main(String[] args) throws Exception {
      // test logins
      checkLoginPage();
      // test temperatures
      checkTemperaturePage();
      // test url params
      checkUrlParams();
    }

    private static WebDriver createDriver(){
      ProfilesIni profile = new ProfilesIni();
      FirefoxProfile ffprofile = profile.getProfile("default"); // selenium
      WebDriver driver = new FirefoxDriver(ffprofile);
      return driver;
    }
    private static void checkLoginPage(){
      // The Firefox driver supports javascript
      WebDriver driver = createDriver();

      // Go to the APT Testing Lab home page
      driver.get("http://apt-public.appspot.com/testing-lab-login.html");

      // test different user logins
      APTLabLogin loginPage = PageFactory.initElements(driver, APTLabLogin.class);
      loginPage.testLogins(driver);

      // Check the title of the page
      //System.out.println("Page title is: " + driver.getTitle());

      // close browser
      driver.quit();
    }

    private static void checkTemperaturePage(){
      // test temperatures
      WebDriver temperatureDriver = createDriver();
      temperatureDriver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      APTLabTemperature temperaturePage = PageFactory.initElements(temperatureDriver, APTLabTemperature.class);
      // submit sample temperature
      List<String> testTemps = Lists.newArrayList("-1","-0.01","0","0.01","32","211","211.9","212","213","213.3", "451", "452.01");
      for (int index = 0; index < testTemps.size(); index++){
        String testTemp = testTemps.get(index);
        temperaturePage.checkTemperature(testTemp);
        temperatureDriver.navigate().back();
      }

      // close browser
      temperatureDriver.quit();
    }

    /* if no url entered, page will be:
       <html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>
       else, page will be:
       <html><head><title>Temperature Converter Result</title></head><body><h2>-30 Farenheit = -34.44 Celsius </h2>
       <form action="LookupTemperature" method="post"><!-- OMMITED FOR BREVITY --></form>
       </body></html>
     */ 
    private static void checkUrlParams(){
      // prepare to load a page
      WebDriver driver = createDriver();
      // verify URL
      // http://apt-public.appspot.com/testing-lab-conversion?farenheitTemperature=-30
      // The temperature parameter should be passed in as farenheitTemperature=-40 in the URL;
      //  the parameter "farenheitTemperature" should be case insensitive

      String pageBaseUrl = "http://apt-public.appspot.com/testing-lab-conversion";
      List<String> testUrlParams = Lists.newArrayList(
          "farenheitTemperature=-30",   // known good
          "fahrenheitTemperature=-30",  // proper spelling of fahrenheit (src: wikipedia)
          "FarenheitTemperature=-30",   // first letter upper
          "FARENHEITTEMPERATURE=-30"   // WHY ARE WE YELLING?
          );
      // Go to the APT Testing Lab home page
      for (int index = 0; index < testUrlParams.size(); index++){
        String testUrlParam = testUrlParams.get(index);
        String urlUnderTest = pageBaseUrl + "?" + testUrlParam;
        logInfo("testing " + urlUnderTest);
        // load page
        driver.get(urlUnderTest);
        // verify that it worked
        String titleText = driver.getTitle();
        if(titleText.equals("No Temperature")){
          logErr("broken page URL, not case-insensitive:  " + urlUnderTest);
        }
      }

      // close browser
      driver.quit();
    }

    public static void logInfo(String message){
      log("-I-: " + message);
    }
    public static void logErr(String message){
      log("-E-: " + message);
    }
    public static void log(String message){
      System.out.println(message);
    }
}
