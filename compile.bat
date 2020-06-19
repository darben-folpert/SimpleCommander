@echo off
del classes\*.class
"C:\David\Softwares\jdk-14\bin\javac.exe" -d classes -cp .\lib\laglibjava.jar --module-path %PATH_TO_FX% --add-modules javafx.controls @source-files.txt
if errorlevel 1 (
  echo This is not good
) else (
echo This is good
)
pause
