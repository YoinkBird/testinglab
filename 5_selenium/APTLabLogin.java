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

    public void testVariousLogins(WebDriver driver){
      this.driver = driver;
      // Enter the username
      // usernames:  andy, bob and charley
      // passwords: apple, bathtub, china
      // TODO: warning! make sure both lists are same length
      List<String> userNames = getUserNames();
      List<String> userPasswords = getUserPassWords();
      // try all permutations of usernames and passwords
      for (int indexUser = 0; indexUser < userNames.size(); indexUser++){
        String userName = userNames.get(indexUser);
        for (int indexPassWord = 0; indexPassWord < userPasswords.size(); indexPassWord++){
          String userPass = userPasswords.get(indexPassWord);
          boolean success = login(userName, userPass);
          // valid user+pass are at same indexUser
          // if valid ...
          if(indexUser < 3 && indexUser == indexPassWord){
            if(success){
              APTTestingLab.logInfo("expected: successful login - user: " + userName + " pass: " + userPass);
            }else{
              APTTestingLab.logErr("unexpected: unsuccessful login should have been valid - user: " + userName + " pass: " + userPass);
            }
          }
          // if non-valid ...
          if(indexUser != indexPassWord){
            if(success){
              APTTestingLab.logErr("unexpected: successful login should have been invalid - user: " + userName + " pass: " + userPass);
            }else{
              APTTestingLab.logInfo("expected: unsuccessful login - user: " + userName + " pass: " + userPass);
            }
          }
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
        loginSuccess = false;
        loginAttempts++;
        // try up to 3 times
        int loginLimit = 3;
        if(loginAttempts < loginLimit){
          long frequentTimeOut = 10000;
          APTTestingLab.logInfo("frequent login timeout - waiting " + frequentTimeOut / 1000 + " seconds, then trying attempt " + loginAttempts + "/" + loginLimit);
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
        //APTTestingLab.logInfo("finally! wait is over. user: " + userName + " pass: " + userPass);
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
