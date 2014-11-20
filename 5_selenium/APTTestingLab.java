//package org.openqa.selenium.example;

import java.util.List;
import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

// explanation http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
// design: see https://code.google.com/p/selenium/wiki/PageFactory
public class APTTestingLab {
    public static void main(String[] args) throws Exception {
      // test logins
      checkLoginPage();
    }

    private static void checkLoginPage(){
      // The Firefox driver supports javascript
      WebDriver driver = new FirefoxDriver();

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
}
