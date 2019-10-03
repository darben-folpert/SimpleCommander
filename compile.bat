@echo off
del classes\*.class
"C:\Program Files\Java\jdk1.8.0_131\bin\javac.exe" -d classes -cp .\lib\laglibjava.jar @source-files.txt
pause
