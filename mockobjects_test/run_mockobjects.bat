
set RESDIR=..\res
set this_classpath=%this_classpath%;%RESDIR%\mockobjects-0.6-core.jar
set this_classpath=%this_classpath%;%RESDIR%\mockobjects-0.6-j1.4-j2ee1.2.jar
set this_classpath=%this_classpath%;%RESDIR%\selenium-server-standalone-2.11.0.jar

set this_javac_files=TestTempServlet.java TemperatureServlet.java
set this_java_target=TestTempServlet

javac -cp %this_classpath% %this_javac_files%

java -cp %CD%;%this_classpath% org.junit.runner.JUnitCore %this_java_target%

REM cleanup; littering is bad
set RESDIR=
set this_classpath=
set this_java_target=
set this_javac_files=
