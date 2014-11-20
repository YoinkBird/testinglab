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

    public void testVariousLogins(WebDriver driver){
      this.driver = driver;
      // Enter the username
      // usernames:  andy, bob and charley
      // passwords: apple, bathtub, china
      // TODO: warning! make sure both lists are same length
      List<String> userNames = getUserNames();
      List<String> userPasswords = getUserPassWords();
      // try all permutations of usernames and passwords
      for (int index = 0; index < userNames.size(); index++){
        String userName = userNames.get(index);
        for (int j = 0; j < userPasswords.size(); j++){
          String userPass = userPasswords.get(j);
          login(userName, userPass);
          driver.navigate().back();
        }
      }
    }

    private boolean login(String userName, String userPass){
      return login(userName, userPass, 0);
    }
    private boolean login(String userName, String userPass, int loginAttempts){
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
        loginAttempts++;
        // try up to 3 times
        int loginLimit = 3;
        if(loginAttempts < loginLimit){
          long frequentTimeOut = 10000;
          APTTestingLab.logInfo("waiting " + frequentTimeOut / 1000 + " seconds, then trying attempt " + loginAttempts + "/" + loginLimit);
          try{
            Thread.sleep(frequentTimeOut);
          } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          driver.navigate().back();
          loginSuccess = this.login(userName, userPass, loginAttempts);
        }
      }
      else if(titleText.equals("Online temperature conversion calculator")){
        //APTTestingLab.logInfo("successful login - user: " + userName + " pass: " + userPass);
        loginSuccess = true;
      }
      if(loginAttempts > 0){
        APTTestingLab.logInfo("finally! wait is over. user: " + userName + " pass: " + userPass);
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
