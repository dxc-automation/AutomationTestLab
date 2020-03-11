set ProjectPath=D:\Frameworks\AutomationTestLab\target
cd %ProjectPath%
java -cp tests.jar org.testng.TestNG D:\Frameworks\AutomationTestLab\src\main\resources\xml_files\TestCase_Takeaway.xml
pause