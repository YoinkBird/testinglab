set RESDIR=..\res
set this_classpath=%this_classpath%;%RESDIR%\junit\4.10\junit-4.10.jar
set this_classpath=%this_classpath%;%RESDIR%\emma.jar

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

REM https://bugs.debian.org/cgi-bin/bugreport.cgi?bug=747027#10
set this_javac_options=-source 1.6 -target 1.6
javac %this_javac_options% -cp %this_classpath% %this_javac_files%

java -cp %this_classpath% emmarun -sp %CD% -r html -cp %CD%;%this_classpath% %this_java_target%

REM cleanup; littering is bad
set RESDIR=
set this_classpath=
set this_classname=
set this_java_target=
set this_javac_files=
set this_javac_options=
