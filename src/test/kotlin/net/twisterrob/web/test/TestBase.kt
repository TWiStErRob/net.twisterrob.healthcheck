package net.twisterrob.web.test

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver

abstract class TestBase {

	protected lateinit var driver: WebDriver
		private set

	@BeforeEach
	fun driverSetUp() {
		driver = Browser.driver
	}

	@AfterEach
	fun driverTearDown() {
		driver.quit()
	}
}
