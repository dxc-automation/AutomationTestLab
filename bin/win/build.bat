set ProjectPath=D:\Frameworks\AutomationTestLab
cd %ProjectPath%
start mvn clean package -Dmaven.test.skip=true
