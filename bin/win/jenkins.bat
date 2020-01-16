set ProjectPath=$WORKSPACE
cd %ProjectPath%\target
java -jar JAVA_FRAMEWORK-1.0-jar-with-dependencies.jar org.testng.TestNG -cp $WORKSPACE\src\main\resources\xml_files\Amazon.xml
pause