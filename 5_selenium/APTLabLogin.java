//package org.openqa.selenium.example;

import java.util.List;
import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

// explanation http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
public class APTLabLogin {
    private WebDriver driver;
    private WebElement userId;
    private WebElement userPassword;

    public void testLogins(WebDriver driver){
      // Enter the username
      // usernames:  andy, bob and charley
      // passwords: apple, bathtub, china
      // TODO: warning! make sure both lists are same length
      List<String> userNames = getUserNames();
      List<String> userPasswords = getUserPassWords();
      for (int index = 0; index < userNames.size(); index++){
        String userName = userNames.get(index);
        String userPass = userPasswords.get(index);
        login(userName, userPass);
        driver.navigate().back();
      }
    }

    private void login(String userName,String userPass){
      userId.clear();
      userId.sendKeys(userName);
      userPassword.clear();
      userPassword.sendKeys(userPass);
      // "press enter"
      userPassword.submit();
    }

    // test-data related methods
    private static List<String> getUserNames(){
      List<String> userNames = Lists.newArrayList(
          "andy",
          "bob",
          "charlie",
          "charlie",
          "hacker"
          );
      return userNames;
    }
    private static List<String> getUserPassWords(){
      List<String> userPassWords = Lists.newArrayList(
          "apple",
          "bathtub",
          "china",
          "sorry",
          "china"
          );
      return userPassWords;
    }
}
