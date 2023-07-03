# Amazon_Testing_Framework
The purpose of a Test Automation Framework is to provide a structured and standardized approach for designing, organizing, and executing automated tests. It is a set of guidelines, best practices, and reusable components that facilitate the development and maintenance of automated test scripts.

For creating this Testing framework, I considered the following main points:
+ To be able to create automated tests quickly by using appropriate abstraction layers
+ The framework should have meaningful logging and reporting structure
+ Should be easily maintainable and extendable
+ Should be simple enough for testers to write automated tests
+ A retry mechanism to rerun failed tests - this is especially useful for WebDriver UI tests

![image](https://github.com/AndreiBanu1/Amazon_Full_Testing_Framework/assets/93880802/be5283f9-4aae-4b6b-91b9-25ee1cc5667f)

This framework will be based on the Page Object Model design pattern. It will also be using the WebDriver’s PageFactory class to initialize WebElements.

The project structure is compose of 4 modules:
- Domain;
- Framework;
- Page-Objects;
- Regression Tests;

##Framework Module

###BasePage.java
All page object classes will extend the BasePage, thus inheriting all the base methods.
BasePage class will have a constructor which takes a WebDriver object to initialize a WebDriverWait object. The constructor will also be responsible to initialize WebElements via PageFactory. In addition, it will also have some utility wait methods to handle the various waits such as WaitForElementToAppear.
