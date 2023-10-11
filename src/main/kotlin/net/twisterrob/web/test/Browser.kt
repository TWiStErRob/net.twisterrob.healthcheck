package net.twisterrob.web.test

import org.apache.logging.log4j.io.IoBuilder
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chromium.ChromiumDriverLogLevel
import java.time.Duration

object Browser {

	// Automatically will use https://www.selenium.dev/documentation/selenium_manager/.

	fun createDriver(): WebDriver {
		val options = ChromeOptions().apply {
			if (Options.headless) {
				addArguments("--headless=new")
				// Implicit via driver.manage() below, it doesn't work with new headless mode.
				//addArguments("--window-size=1920,1080")
			}
		}
		val service = ChromeDriverService.Builder()
			.withLogLevel(ChromiumDriverLogLevel.WARNING)
			.build()
			.apply {
				// Redirect driver's output and error streams to proper logger. It'll show as:
				// > [Exec Stream Pumper] org.openqa.selenium.chrome.ChromeDriver(MultiOutputStream.java:40)
				// Uses platform's encoding via Charset.defaultCharset().
				sendOutputTo(IoBuilder.forLogger(ChromeDriver::class.java).buildOutputStream())
			}
		val driver = ChromeDriver(service, options)
		driver.manage().apply {
			timeouts().implicitlyWait(Duration.ofSeconds(10))
			// Ensure there's a fixed size, so tests behave the same, this is necessary in headless too.
			window().size = Dimension(1920, 1080)
		}
		return driver
	}
}
