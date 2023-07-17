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

Framework Module: This module contains the core framework components for the Amazon testing framework. It includes classes like BasePage.java and BaseTest.java, which serve as base models for page objects and test cases respectively. These classes provide common functionalities and utilities that can be used across the entire testing framework.

Domain Module: The domain module encapsulates the domain-specific functionality and business logic of the Amazon application. It defines data models, implements business rules, and provides services or utilities specific to the application's domain. This module ensures that the testing framework aligns with the application's core functionality and behaviors.

Page Objects Module: This module focuses on the implementation of Page Object Models (POMs) for the Amazon application. It contains the Page Model Factory, where all the web elements to be tested are declared. The POMs help in organizing the web elements and actions associated with specific pages, making the tests more readable, maintainable, and reusable.

Regression Tests Module: The regression tests module houses the actual test cases for the Amazon application. It utilizes the framework, domain, and page objects modules to create comprehensive regression tests. These tests verify the functionality of the application and ensure that new changes or updates do not introduce regressions or issues.

Each child module in this Maven project plays a specific role in creating a robust and maintainable testing framework for the Amazon application. The separation of concerns allows for modular development, easier debugging, and scalability as the project grows.

#Framework Module

##Core module
This module will contain the BasePage.java and BaseTest.java files and also any other class to be extended in future usage.

**BaseTest.java**: Provides the base class for all test classes. It includes common setup and teardown methods for initializing the WebDriver and handling test lifecycle.
**BasePage.java**: Represents the base page object class that contains common methods and functionalities for interacting with web elements.

### Domain Module

- **LoginCredentials.java**: Stores the login credentials (username and password) securely. It loads the credentials from the `credentials.txt` file located in the resources directory.
- **LoginService.java**: Implements the login service, which provides methods for performing login operations.
- **UserService.java**: Represents the user service, which interacts with user-related functionalities in the application.

### Page Objects Module

- **LoginPage.java**: Defines the page object class for the login page. It includes methods to interact with the login page elements such as entering credentials and clicking buttons.
- **HomePage.java**: Represents the page object class for the home page. It provides methods for interacting with elements on the home page.

### Regression Tests Module

- **LoginRegressionTest.java**: Contains regression tests for the login functionality. It uses the page objects and the framework's base test class to write test cases for login scenarios.

This project structure provides a modular and organized approach to develop and test the application. Each module has specific responsibilities, enabling better maintainability and testability of the codebase.

The project contains the following test scenerios: </br>
  I. Login Module:
  a) test with valid credentials;
  b) test with invalid username;
  c) test with valid username and invalid password;

II. Search Module:
a) Basic Search:
    - Test that you can enter a keyword in the search bar and perform a search.
    - Verify that the search results page is displayed, showing products related to the entered keyword.

b) Advanced Search:
    - Test the advanced search options such as filtering by category, brand, price range, ratings, etc.
    - Verify that the search results reflect the selected filters accurately.

c) Empty Search:
    - Test performing a search without entering any keyword.
    - Verify that an appropriate error message is displayed, prompting the user to enter a valid keyword.

d) Search Suggestions:
    - Test the search suggestions feature that provides auto-complete suggestions while typing in the search bar.
    - Verify that relevant suggestions are displayed based on the entered keywords.

e) Search Results Sorting:
    - Test the sorting options on the search results page (e.g., relevance, price low to high, price high to low, customer ratings, etc.).
    - Verify that the sorting order of the search results is accurate as per the selected sorting option.

f) Pagination:
    - Test the pagination feature on the search results page.
    - Verify that the page navigation options (previous page, next page) are working correctly, and the correct results are displayed when navigating through different pages.

g) Product Details from Search Results:
    - Test clicking on a product from the search results to view its details.
     - Verify that the product details page is displayed with accurate information, including the product title, price, description, and customer reviews.

h) Search Performance and Speed:
    - Test the search performance and speed by entering various keywords and monitoring the response time.
    - Verify that the search results are returned within an acceptable time frame.

i) Search Result Validation:
    - Test the accuracy of the search results by searching for specific products or known keywords.
    - Verify that the expected products are displayed in the search results and that irrelevant or unrelated products are not included.

j) Search Across Multiple Categories:
    - Test performing searches across different categories (e.g., books, electronics, clothing, etc.).
    - Verify that the search results are relevant and specific to the selected category.

k) Search Result Images:
    - Test that the search results display appropriate product images.
    - Verify that the images are of good quality, properly aligned, and represent the corresponding products accurately.

l) Search Result Link Validity:
    - Test clicking on various search result links to ensure that they are valid and lead to the correct product or category pages.
