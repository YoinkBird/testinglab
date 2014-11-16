@ echo off
REM http://junit.sourceforge.net/doc/faq/faq.htm#started
REM set JUNIT_HOME=%CD%\res\junit
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_25"
set JUNIT_HOME=%CD%\res\junit\4.10
set JUNIT_CLASSPATH=%JUNIT_HOME%\junit-4.10.jar
IF DEFINED CLASSPATH (
  REM set CLASSPATH=%CLASSPATH%;%JUNIT_CLASSPATH%
  echo "overwriting classpath"
  set CLASSPATH=%JUNIT_CLASSPATH%
) ELSE ( 
  set CLASSPATH=%JUNIT_CLASSPATH%
)


set CLASSPATH=
set CLASSPATH=%JUNIT_CLASSPATH%
set JUNIT_CLASSPATH=

echo "Note: must either set CLASSPATH=%%CLASSPATH%%;%%CD%% or run javac and java with -cp %%CLASSPATH%%;%%CD%%"


REM echo envvars
setup_verify.bat
