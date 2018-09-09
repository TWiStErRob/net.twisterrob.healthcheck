package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.test.assertEquals

class IndexPage(driver: WebDriver) : BasePage(driver) {

	@FindBy(css = "h1")
	private lateinit var heading: WebElement

	override fun open() {
		check(!::heading.isInitialized) { "Already initialized" }
		driver.get("http://cinema.twisterrob.net/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		driver.get("http://cinema.twisterrob.net/")
		assertEquals("cinema.twisterrob.net", driver.title)
		assertEquals("Cineworld Server API", heading.text)
	}
}
