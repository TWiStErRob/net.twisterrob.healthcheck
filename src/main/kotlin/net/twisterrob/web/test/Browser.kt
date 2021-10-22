package net.twisterrob.web.test

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.time.Duration

object Browser {

	init {
		System.setProperty("webdriver.chrome.driver", findSeleniumChromeDriverExecutable().absolutePath)
	}

	private fun findSeleniumChromeDriverExecutable(): File =
		File("node_modules/webdriver-manager/selenium")
			.listFiles { _, fileName: String ->
				// Not .zip means .exe or no extension for unix.
				fileName.startsWith("chromedriver_") && !fileName.endsWith(".zip")
			}
			.orEmpty()
			.single()

	fun createDriver(): WebDriver {
		val driver = ChromeDriver(ChromeOptions().apply {
			if (Options.headless) {
				addArguments("--headless")
			}
		})
		driver.manage().apply {
			timeouts().implicitlyWait(Duration.ofSeconds(10))
			if (Options.headless) {
				// Make sure the window has a size,
				// because "maximize()" is not enough when there's no window system.
				window().size = Dimension(1920, 1080)
			}
			window().maximize()
		}
		return driver
	}
}
