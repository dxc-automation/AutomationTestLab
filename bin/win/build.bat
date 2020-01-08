set ProjectPath=E:\TestLabTestNG
cd %ProjectPath%
start mvn clean package -Dmaven.test.skip=true
