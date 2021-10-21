package net.twisterrob.web.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.util.concurrent.TimeUnit

object Browser {

	init {
		System.setProperty(
			"webdriver.chrome.driver",
			File("node_modules/webdriver-manager/selenium")
				.listFiles { _, fileName: String -> fileName.startsWith("chromedriver_") }!!
				.filterNot { it.extension == "zip" }
				.single()
				.absolutePath
		)
	}

	val driver: WebDriver
		get() {
			val driver = ChromeDriver()
			driver.manage().apply {
				timeouts().implicitlyWait(10, TimeUnit.SECONDS)
				window().maximize()
			}
			return driver
		}
}
