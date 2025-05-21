@echo off
cd /d "%~dp0"
java --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar "Proyecto_FFE.jar"
pause