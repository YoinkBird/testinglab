set RESDIR=..\res
set this_classpath=%this_classpath%;%RESDIR%\junit\4.10\junit-4.10.jar

set this_classname=RationalTest
set this_javac_files=%this_classname%.java
set this_java_target=%this_classname%
echo %this_classname%
set this_classname=Rational
set this_javac_files=%this_javac_files% %this_classname%.java
set this_java_target=%this_java_target% %this_classname%
echo %this_classname%

REM remove class files, verify by listing dir contents
del *class
dir

javac -cp %this_classpath% %this_javac_files%

java -cp %CD%;%this_classpath% %this_java_target%


REM cleanup; littering is bad
set RESDIR=
set this_classpath=
set this_classname=
set this_java_target=
set this_javac_files=
