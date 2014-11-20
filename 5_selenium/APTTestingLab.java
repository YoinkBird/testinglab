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
      loginPage.testLogins();

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
      temperaturePage.checkTemperature();

      // close browser
      //temperatureDriver.quit();
    }
}
