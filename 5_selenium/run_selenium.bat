REM https://code.google.com/p/selenium/wiki/GettingStarted

set RESDIR=..\res
set this_classpath=%this_classpath%;%RESDIR%\selenium-server-standalone-2.44.0.jar

set this_classname=APTTestingLab
set this_javac_files=%this_classname%.java
set this_java_target=%this_classname%
echo %this_classname%
javac -cp %this_classpath% %this_javac_files%

java -cp %CD%;%this_classpath% %this_java_target%


REM cleanup; littering is bad
set RESDIR=
set this_classpath=
set this_classname=
set this_java_target=
set this_javac_files=
