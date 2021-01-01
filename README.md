# gmailAutomation

Automation Task - Gmail
--------------------------------
Testing Tools Used - Selenium , TestNG, Log4J, Apache POI ( for Reading the Values from excel )
Approach - Data Driven
Design Pattern - Page Object Model ( POM )

Execution Steps
-------------------
1) Extract the Zip in Local.
2) Import the Project contents as Maven Existing Projects
3) Once the Project has been imported into IDE, update the maven project
4) Replace the chrome driver version against the build which you are using in your local
5) Right click testNG.xml file and Run as TestNG Suite
6) The Logs will be genereated and stored in a file named "GmailTest.log"
7) The failure screenshot will be captured and stored inside the screenshots folder.
8) To change the dummy mail address, mail subject, recepient details and mail content. Please do the necessary changes in below file path.
Directory Name - dataprovider 
File name - TestData.xlsx
9) TestNG Reports will be published inside the test-output folder.

Thanks for Reading !!
