REM dependencies:
REM mockobjects-0.6-core.jar
REM mockobjects-0.6-j1.4-j2ee1.2.jar
REM selenium-server-standalone-2.11.0.jar

REM sets up java_home, junit_home, adds junit to classpath
setup_junit.bat

javac -cp %CD%;%CLASSPATH%;mockobjects-0.6-core.jar;mockobjects-0.6-j1.4-j2ee1.2.jar TemperatureServlet.java TemperatureServlet.java

