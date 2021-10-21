## Structure
 * `src/main/kotlin` is the test framework built on Selenium.
 * `src/test/kotlin` are the health checks implemented in JUnit 5.

## Running

`gradlew smokeTest` to run some quick tests.

## Entry points

 * `*Test` files

## Configuration
 
 * `net.twisterrob.test.selenium.headless` to set up ChromeDriver in headless mode. Used for running on GitHub actions.

## Deployment

Only CI.
