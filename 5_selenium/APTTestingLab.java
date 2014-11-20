//package org.openqa.selenium.example;

import java.util.List;
import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

// explanation http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
public class APTTestingLab {
    private static WebDriver driver;
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        driver = new FirefoxDriver();
        
        // Go to the APT Testing Lab home page
        driver.get("http://apt-public.appspot.com/testing-lab-login.html");

        // test different user logins
        testLogins();
        


        // Check the title of the page
        //System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }

    private static void testLogins(){
      // Enter the username
      // usernames:  andy, bob and charley
      // passwords: apple, bathtub, china
      // TODO: warning! make sure both lists are same length
      List<String> userNames = Lists.newArrayList("andy");
      List<String> userPasswords = Lists.newArrayList("apple");
      for (int index = 0; index < userNames.size(); index++){
        String userName = userNames.get(index);
        String userPass = userPasswords.get(index);
        login(userName, userPass);
      }
    }

    private static void login(String userName,String userPass){
      WebElement query = driver.findElement(By.name("userId"));
      query.clear();
      query.sendKeys(userName);
      query = driver.findElement(By.name("userPassword"));
      query.clear();
      query.sendKeys(userPass);
      // "press enter"
      query.submit();
    }
}
