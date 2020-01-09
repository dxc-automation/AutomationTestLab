set ProjectPath=D:\Frameworks\AutomationTestLab
cd %ProjectPath%\target
java -cp JAVA_FRAMEWORK-1.0-jar-with-dependencies.jar org.testng.TestNG D:\Frameworks\AutomationTestLab\src\main\resources\xml_files\EPAM.xml
pause