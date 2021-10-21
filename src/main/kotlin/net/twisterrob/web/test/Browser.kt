package net.twisterrob.web.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.time.Duration

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
			val driver = ChromeDriver(ChromeOptions().apply {
				if (Options.headless) {
					addArguments("--headless")
				}
			})
			driver.manage().apply {
				timeouts().implicitlyWait(Duration.ofSeconds(10))
				window().maximize()
			}
			return driver
		}
}
