REM set selenium_cp=%CD%;selenium-server-standalone-2.44.0.jar
REM https://code.google.com/p/selenium/wiki/GettingStarted
echo "Example"
javac -cp selenium-server-standalone-2.44.0.jar Example.java
java -cp %CD%;selenium-server-standalone-2.44.0.jar Example

echo "GoogleSuggest:"
javac -cp selenium-server-standalone-2.44.0.jar GoogleSuggest.java
java -cp %CD%;selenium-server-standalone-2.44.0.jar GoogleSuggest


REM set selenium_cp=
