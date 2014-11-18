REM copy in files
copy ..\res\mockobjects-0.6-core.jar
copy ..\res\mockobjects-0.6-j1.4-j2ee1.2.jar
copy ..\res\selenium-server-standalone-2.11.0.jar

javac -cp %CLASSPATH%;mockobjects-0.6-core.jar;mockobjects-0.6-j1.4-j2ee1.2.jar;selenium-server-standalone-2.11.0.jar TestTempServlet.java TemperatureServlet.java

java -cp %CD%;%CLASSPATH%;mockobjects-0.6-core.jar;mockobjects-0.6-j1.4-j2ee1.2.jar;selenium-server-standalone-2.11.0.jar org.junit.runner.JUnitCore TestTempServlet
