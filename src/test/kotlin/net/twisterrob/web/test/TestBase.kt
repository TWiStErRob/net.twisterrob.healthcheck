package net.twisterrob.web.test

import org.junit.After
import org.junit.Before
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.net.URI
import java.util.concurrent.TimeUnit

abstract class TestBase {

	protected lateinit var driver: WebDriver

	@Before
	fun driverSetUp() {
		driver = Browser.driver
	}

	@After
	fun driverTearDown() {
		driver.quit()
	}
}
