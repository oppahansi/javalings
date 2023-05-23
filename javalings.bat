@ECHO OFF

ECHO Starting javalings...

.\gradlew.bat run --console=plain --args="%1 %2" -q