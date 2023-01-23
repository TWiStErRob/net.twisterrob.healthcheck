package net.twisterrob.web.test

import io.github.bonigarcia.wdm.WebDriverManager
import org.apache.logging.log4j.io.IoBuilder
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chromium.ChromiumDriverLogLevel
import java.time.Duration

object Browser {

	init {
		WebDriverManager.chromedriver().setup()
	}

	fun createDriver(): WebDriver {
		val options = ChromeOptions().apply {
			if (Options.headless) {
				// Make sure the window has a size,
				// because "maximize()" alone is not enough when there's no window system.
				// Alternative setup: driver.manage().window().size = Dimension(1920, 1080)
				addArguments("--headless", "--window-size=1920,1080")
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
			window().maximize()
		}
		return driver
	}
}
