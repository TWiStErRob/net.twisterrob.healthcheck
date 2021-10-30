package net.twisterrob.web.test

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.slf4j.LoggerFactory
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.time.Duration
import kotlin.concurrent.thread

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
			sendOutputTo(loggerStream(LoggerFactory.getLogger(ChromeDriver::class.java)::info))
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

	/**
	 * https://stackoverflow.com/a/69782433/253468
	 */
	private fun loggerStream(outputLine: (line: String) -> Unit): PipedOutputStream {
		val output = PipedOutputStream()
		val input = PipedInputStream(output).bufferedReader()
		thread(isDaemon = true) {
			input.lineSequence().forEach(outputLine)
		}
		return output
	}
}
