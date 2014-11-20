//package org.openqa.selenium.example;

import java.util.List;
import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

// explanation http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
public class APTTestingLab {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        
        // Go to the APT Testing Lab home page
        driver.get("http://apt-public.appspot.com/testing-lab-login.html");
        
        // Enter the username
        // usernames:  andy, bob and charley
        // passwords: apple, bathtub, china
        WebElement query = driver.findElement(By.name("userId"));
        query.clear();
        query.sendKeys("andy");
        query = driver.findElement(By.name("userPassword"));
        query.clear();
        query.sendKeys("apple");

        // "press enter"
        query.submit();

        // Check the title of the page
        //System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }
}
