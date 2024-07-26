# Appscrip_Assignent

## Project Description

`TruelyFreeHome(https://trulyfreehome.dev/)` is a test automation project developed from scratch using Gradle. 
 The project includes multiple test cases for a web application, covering functionalities like account creation, navigation through a mega menu, product listing, product details page interactions, cart operations, and the checkout process.
 The project also generates product details and writes them into an Excel file.

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Gradle
- Apache POI (for Excel operations)

## Setup

To set up the project locally, follow these steps:

1. **Initialize the project using gradle init command**

    
2. **Add dependencies:**
   Added dependencies for Selenium, TestNG, and Apache POI in the build.gradle file.

3. **Setup WebDriver:**

   Configured ChromeDriver and set up essential utilities like JavascriptExecutor and WebDriverWait in the @BeforeSuite method.

## Usage

To run the test cases, use the following Gradle command: ./gradlew test

`
