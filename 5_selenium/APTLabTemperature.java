//package org.openqa.selenium.example;

import java.math.BigDecimal;
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

      // get temperature string from webpage
      String retTemp = getCalculatedTemp();
      // precision has strict requirements
      checkPrecision(temperature, retTemp);

    }

    // parse "testing-lab-conversion" page for html element with temperature conversion
    // return float representation of calculated temperature
    public String getCalculatedTemp(){
      String returnTemp = null;
      if(header != null){
        String headerText = header.getText();
        System.out.println("page header: " + headerText);
        // src: http://www.tutorialspoint.com/java/java_regular_expressions.htm
        // format: <h2>32 Farenheit = 0 Celsius </h2>
        String tempRegEx = "[-\\d\\.]+";
        String pattern = "(" + tempRegEx + ") Farenheit = (" + tempRegEx + ") Celsius";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(headerText);

        if(m.find()){
          String tempF = m.group(1).trim();
          String tempC = m.group(2).trim();
          System.out.println("      tempF: " + tempF + " tempC: " + tempC);
          returnTemp = tempC;
        }
      }
      else{
        System.out.println("header undefined");
      }
      return returnTemp;
    }

    // Temperature results should be 2 places of precision for temperatures from 0 to 212 degrees Farenheit, inclusive, and 1 place of precision otherwise.
    public void checkPrecision(String sTempF, String tempC){
      float fTempF = Float.parseFloat(sTempF);
      float fTempC = Float.parseFloat(tempC);

      BigDecimal bdTempF = new BigDecimal(sTempF);
      BigDecimal bdTempC = new BigDecimal(tempC);
      /*
      // quick check that it is working
      System.out.println("  BigDecimal - scale     :" + " F: " + bdTempF.scale() + " C: " + bdTempC.scale());
      System.out.println("  BigDecimal - precision :" + " F: " + bdTempF.precision() + " C: " + bdTempC.precision());
      */
      // check precision on string, e.g. BigDecimal scale()
      // temperatures from 0 to 212 degrees Farenheit, inclusive:  2 places of precision
      int iRequiredScale = 0;
      if(fTempF >= 0 || fTempF <= 212){
        iRequiredScale = 2;
        if(bdTempC.scale() != iRequiredScale){
          logErr("temperature " + tempC + " C does not have " + iRequiredScale + " decimals precision, has " + bdTempC.scale() + " decimals");
          log(" Got a NumberFormatException on " + sTempF);
        }else{
          logInfo("temperature " + tempC + " C has" + iRequiredScale + " decimals precision, correct amount");
        }
      }else{
        iRequiredScale = 1;
        if(bdTempC.scale() != iRequiredScale){
          logErr("temperature " + tempC + " C does not have " + iRequiredScale + " decimals precision, has " + bdTempC.scale() + " decimals");
          log(" Got a NumberFormatException on " + sTempF);
        }else{
          logInfo("temperature " + tempC + " C has " + iRequiredScale + " decimals precision, correct amount");
        }
      }

    }
    public void logInfo(String message){
      log("-I-: " + message);
    }
    public void logErr(String message){
      log("-E-: " + message);
    }
    public void log(String message){
      System.out.println(message);
    }
}

