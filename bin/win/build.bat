set ProjectPath=C:\Automation\AutomationTestLab
cd %ProjectPath%
start mvn clean package -Dmaven.test.skip=true