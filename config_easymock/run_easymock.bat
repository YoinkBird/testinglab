
set RESDIR=..\res
set this_classpath=%this_classpath%;%RESDIR%\junit\4.10\junit-4.10.jar
set this_classpath=%this_classpath%;%RESDIR%\easymock-3.2\easymock-3.2.jar
set this_classpath=%this_classpath%;%RESDIR%\objenesis-tck-1.2.jar
set this_classpath=%this_classpath%;%RESDIR%\cglib-nodep-2.2.2.jar

set this_javac_files=TestJukebox.java
set this_java_target=TestJukebox

javac -cp %this_classpath% %this_javac_files%

java -cp %CD%;%this_classpath% org.junit.runner.JUnitCore %this_java_target%

REM cleanup; littering is bad
set RESDIR=
set this_classpath=
set this_java_target=
set this_javac_files=
