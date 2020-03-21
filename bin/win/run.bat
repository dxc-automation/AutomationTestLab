set ProjectPath=D:\Frameworks\AutomationTestLab\target
cd %ProjectPath%
java -cp tests.jar org.testng.TestNG  -Denvironment=internal -Dbrowser=chrome D:\Frameworks\AutomationTestLab\src\main\resources\xml_files\TestCase_01.xml
pause