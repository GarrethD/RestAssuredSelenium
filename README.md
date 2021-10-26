# Introduction

Hi!

My name is Garreth and I am the author of this framework. This framework was written in Java, and it's built on top of
the TestNG testing framework. The reason for choosing Java and TestNG is rather a simple one. The abundance of
information and resources on the internet makes it a very easy choice. Pairing selenium with TestNG and java not only
makes sense from an information resource point of view, but also from an integration POV. Maven integrates really well
with the most popular CI/CD tools out there like GitLab/GitHub/Jenkins/Travis and AzureDevops.

# Overview

The framework is broken down into 5 core components:

1. The Base Package. This holds all the Api methods used for testing. These can live in another class and be split up
   into multiple different classes but generally this is where all the reusable methods reside.

2. The Driver class is the core of the framework. This is where all the selenium methods reside and where we choose what
browser we want to test against. Without this class there is no framework.

3. The Env_Configuration package. This package links the tests to Jenkins as well as handles a few things like getting the environment as well as receiving Jenkins parameters if used.
In this package we set Enums for users and URLs that we can switch dynamically while testing. We can add Enums for many things but for now URLs and Users work perfectly.

4. PageObjects Package. This package holds the pageObjects classes of the system being tested. 
A page object can be anything such as a Xpath,CSS Selector,ID,Name,TagName,LInkText etc.
This way if we test any system, 2 weeks to 2 years down the line they update the frontend, the user only needs to update the pageObjects in a single place, and it will update across all the tests.

5. The test package. This package houses the classes of the actually test cases. The structure from Test case to automation is as follows:

# Test Structure
**Epic** = The Package name of the area being tested.

**Scenario** = The class name.

**@Test** annotation within a class = the test cases linked to the scenario being tested.

# Prerequisites:
*Java jdk-1.8 or higher

*Apache Maven 3 or higher

*Please refer for any help in Maven.

*http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

*http://www.tutorialspoint.com/maven/maven_environment_setup.htm

# A. Build and Test:
To build the test on a local machine do the following:

1. Clone the project
2. Open the project in a Java enabled IDE
3. Right-click on the .xml of choice to run and click execute.
4. Results will be printed out in ExtentReports folder.

OR

_Via windows command line run the following commands:_

**mvn clean test -Dsurefire.suiteXmlFiles=TestNG_Runner_All.xml**

To run different packs like UI or API or both, substitute the suiteXmlFile.
i.e

**TestNG_Runner_API.xml**  - for just the APIs.

**TestNG_Runner_UI.xml**  - for just the UI tests.

**TestNG_Runner_All.xml**  - for all tests.

Many combinations can be set to run smoke packs, regression packs , different browsers, environments etc.

# B. How to run tests in CI/CD
1. Log into GitHub and navigate to the automation repo.
2. Click on actions from the repo page
3. Select the Workflow you would like to run (In this case it's the 'Java CI with maven' workflow)
4. click on the 'Run workflow' | Enter the name of the person executing the flow
5. Done! 

# Reporting:
The framework produces an ExtentReportResults.html file that resided in the '**ExtentReports**' folder along with any images taken upon failure. The Reporting is handled by a free opensource library called 'Extent Reports'.
More information around ExtentReports can be found at : https://www.extentreports.com/

# C. Calliope Pro Report upload link:

https://app.calliope.pro/reports/104855/public/8fed026e-c307-443c-a65d-6fe7b75c523f

# D. Approach to selecting Scenarios:

Generally I like to take the approach of that if the most impactful parts of the system is automated then the client or Product owner will gain confidence around automation and testing in general.
Thus, I do feel that it's extremely important to showcase the CRUD of any application. The CRUD refers to Create Edit and delete.
I based all my testing in this project around CRUD.

For UI tests:

Bookstore - scenario
1. Log in
2. Add a book to user collection
3. Delete book from user collection.

Why log in instead of create a user? Simply put, There is currently no way to automate reCaptcha. The only way around reCaptcha is to ask the devs to disable it dev or staging environment
or test around it by doing reCaptcha processes manually. 

For API tests:

Everything about your pet - Scenario
1. Add a pet to the petstore - Testcase 1
2. Edit existing pet to the petstore - Testcase 2
3. Delete pet from the petstore - Testcase 3

Petstore Orders - Scenario
1. Place an order - Testcase 1
2. Look up and order - Testcase 2
3. Delete an order - Testcase 3

User Operations - Scenario
1. Create a user - Testcase 1
2. Edit a user - Testcase 2
3. Delete a user - Testcase 3


# Why are these important?
The structure is the same for any CRUD no matter the application. This shows immediate impact and demonstrates the power of automation very well to new clients interested in Automaton.
No matter the application, generally there is a login portal, signup portal or a user can place an order or search for products. Thus making it very easy to come up with a CRUD example.
When clients approach a consultancy, and they ask for automaton, they generally don't quite understand what that is. They don't understand what goes into testing. This approach is the least effort with most impact.

# Next steps for the project:
I think the next step would be to start filling in some automation test cases. I do feel this framework has a lot to offer, and it's very modular.
If mobile testing is required, It would be as easy as adding a driver class to the Base package that handles all the mobile gestures.
Having it as a separate class makes it more manageable, and it allows a user to do UI and Automation in tandem.

Do you need to do some actions on the mobile and have it reflect on the web based ui? That will be possible.
