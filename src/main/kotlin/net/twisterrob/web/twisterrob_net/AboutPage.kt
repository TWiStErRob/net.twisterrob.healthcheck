package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.initElements
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AboutPage(private val driver: WebDriver) {

	@FindBy(css = "article h1")
	private lateinit var heading: WebElement

	@FindBy(css = "img[alt='picture of me']")
	@JvmSuppressWildcards
	private lateinit var images: List<WebElement>

	fun launch() {
		check(!::heading.isInitialized) { "Already initialized" }
		driver.get("http://www.twisterrob.net/info/")
		driver.initElements(this)
	}

	fun assertLaunched() {
		assertEquals("http://www.twisterrob.net/info/", driver.currentUrl)
		assertEquals("About", driver.title)
		assertEquals("About", heading.text)
	}

	fun assertImageVisible() {
		assertTrue(images.any { it.isDisplayed })
	}
}
