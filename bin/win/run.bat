set ProjectPath=D:\Frameworks\AutomationTestLab
cd %ProjectPath%\target
java -cp tests.jar org.testng.TestNG D:\Frameworks\AutomationTestLab\src\main\resources\xml_files\Demo.xml
pause