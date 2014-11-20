//package org.openqa.selenium.example;

import java.util.List;
import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;

// explanation http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
public class APTLabTemperature {
    private WebElement farenheitTemperature;
    @FindBy(tagName = "h2")
    private WebElement header;


    // http://docs.seleniumhq.org/docs/04_webdriver_advanced.jsp#implicit-waits
    // http://apt-public.appspot.com/testing-lab-calculator.html 
    public void checkTemperature(){
      farenheitTemperature.clear();
      farenheitTemperature.sendKeys("32");
      // "press enter"
      farenheitTemperature.submit();

    }

    public void verifyTemperature(){
      if(header != null){
        String headerText = header.getText();
        System.out.println("page header: " + headerText);
      }
      else{
        System.out.println("header undefined");
      }
    }
}
