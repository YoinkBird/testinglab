//package org.openqa.selenium.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public void checkTemperature(String temperature){
      farenheitTemperature.clear();
      farenheitTemperature.sendKeys(temperature);
      // "press enter"
      farenheitTemperature.submit();

      verifyTemperature();

    }

    public void verifyTemperature(){
      if(header != null){
        String headerText = header.getText();
        System.out.println("page header: " + headerText);
        // src: http://www.tutorialspoint.com/java/java_regular_expressions.htm
        // format: <h2>32 Farenheit = 0 Celsius </h2>
        String tempRegEx = "[\\d\\.]+";
        String pattern = "(" + tempRegEx + ") Farenheit = (" + tempRegEx + ") Celsius";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(headerText);

        if(m.find()){
          String tempF = m.group(1);
          String tempC = m.group(2);
          System.out.println("tempF: " + tempF + " tempC: " + tempC);
        }
      }
      else{
        System.out.println("header undefined");
      }
    }
}
