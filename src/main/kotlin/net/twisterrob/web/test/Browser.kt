package net.twisterrob.web.test

import io.github.bonigarcia.wdm.WebDriverManager
import org.apache.logging.log4j.io.IoBuilder
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration

object Browser {

	init {
		WebDriverManager.chromedriver().setup()
	}

	fun createDriver(): WebDriver {
		val options = ChromeOptions().apply {
			if (Options.headless) {
				addArguments("--headless")
			}
		}
		val service = ChromeDriverService.createServiceWithConfig(options).apply {
			// Uses platform's encoding via Charset.defaultCharset().
			sendOutputTo(IoBuilder.forLogger(ChromeDriver::class.java).buildOutputStream())
		}
		val driver = ChromeDriver(service, options)
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
