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

    public void testValidLogins(WebDriver driver){
      this.driver = driver;
      // Enter the username
      // usernames:  andy, bob and charley
      // passwords: apple, bathtub, china
      // TODO: warning! make sure both lists are same length
      List<String> userNames = getUserNames();
      List<String> userPasswords = getUserPassWords();
      // HACK: only three valid logins
      // TODO: update 'getUser*()' to return 'valid', 'all', 'invalid' values
      for (int index = 0; index < 3; index++){
        String userName = userNames.get(index);
        String userPass = userPasswords.get(index);
        boolean success = login(userName, userPass);
        if(success){
          APTTestingLab.logInfo("successful login - user: " + userName + " pass: " + userPass);
        }else{
          APTTestingLab.logErr("unsuccessful login should have been valid - user: " + userName + " pass: " + userPass);
        }
        driver.navigate().back();
      }
    }

    private boolean login(String userName, String userPass){
      boolean loginSuccess = false;
      userId.clear();
      userId.sendKeys(userName);
      userPassword.clear();
      userPassword.sendKeys(userPass);
      // "press enter"
      userPassword.submit();

      // check page 
      String titleText = driver.getTitle().trim();
      if(titleText.equals("Bad Login")){
        //APTTestingLab.logInfo("failed login - user: " + userName + " password: " + userPass);
        loginSuccess = false;
      }
      else if(titleText.equals("Frequent Login")){
        APTTestingLab.logInfo("frequent login - user: " + userName + " password: " + userPass);
        loginSuccess = false;
      }
      else if(titleText.equals("Online temperature conversion calculator")){
        //APTTestingLab.logInfo("successful login - user: " + userName + " pass: " + userPass);
        loginSuccess = true;
      }

      return loginSuccess;
    }

    // test-data related methods
    private static List<String> getUserNames(){
      List<String> userNames = Lists.newArrayList(
          "andy",
          "bob",
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
          "sorry"
          );
      return userPassWords;
    }
}
