Java Test Project:
Behavior-Driven-Testing, Data-Driven-Testing and CI/CD approaches integration based on www.willhaben.at
(Cucumber-Selenium-JUnit-JDBC-Apache POI-POM(PageFactory)-Maven-Jenkins-BitBucket-JIRA-Integration)

Test object – the logon process on www.willhaben.at
Test feature – three of more possible error alerts.

The current project actually combines two test projects with the same test object and test feature,
which can also be executed separately:

    - as a CUCUMBER / Selenium WebDriver / JUnit / JDBC / POM (Page Factory) implementation
     (starting src/test/java/behaviorDrivenTesting/testRunner/WillhabenLoginTestRunner.java) or
     
    - as a Selenium WebDriver DDT/ JUnit / JDBC / Apache POI / POM (Page Factory) implementation
     (starting src/test/java/dataDrivenTesting/WillhabenLoginTest.java)
     
 All 6 tests included are executed locally on Jenkins. The build is triggered using a BitBucket WebHook and NGROK.
 The whole development process ist managed in JIRA Software Cloud.
 
 Installation instructions:
 After cloning this repository you need to copy the folder 'db_login_data' from
 src/main/resources/testdataAndDriver/db_login_data into MySQL\mysqlx\data of your own MySQL (not included here 
 for space reasons) and to run the DBMS. 