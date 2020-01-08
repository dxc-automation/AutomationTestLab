set ProjectPath=E:\TestLabTestNG
cd %ProjectPath%\target\
java -cp JAVA_FRAMEWORK-1.0-jar-with-dependencies.jar org.testng.TestNG  %ProjectPath%/src/main/resources/xml_files/Fly.xml
pause