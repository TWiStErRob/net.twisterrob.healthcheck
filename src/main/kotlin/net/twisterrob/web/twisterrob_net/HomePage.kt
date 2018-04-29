package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.initElements
import net.twisterrob.selenium.createPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.test.assertEquals

class HomePage(private val driver: WebDriver) {

	@FindBy(id = "logo")
	private lateinit var logo: WebElement

	@FindBy(css = "#navigation .icon-v-card")
	private lateinit var infoLink: WebElement

	fun launch() {
		check(!::logo.isInitialized) { "Already initialized" }
		driver.get("http://www.twisterrob.net/")
		driver.initElements(this)
	}

	fun assertLaunched() {
		// TODO dashes?
		assertEquals("TWiStErRob – Professional & Hobby Development site", driver.title)
		assertEquals("TWiStErRob — Professional & Hobby Development site", logo.getAttribute("title"))
	}

	fun gotoAbout(): AboutPage {
		infoLink.click()
		return driver.createPage()
	}
}
