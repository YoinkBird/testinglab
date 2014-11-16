REM https://bugs.debian.org/cgi-bin/bugreport.cgi?bug=747027#10
set PWD=%CD%
javac -source 1.6 -target 1.6 Rational.java RationalTest.java
java -cp emma.jar emmarun -sp $PWD -r html -cp %CLASSPATH% RationalTest
