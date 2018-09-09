package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.createPage
import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.test.assertEquals

class IndexPage(driver: WebDriver) : BasePage(driver) {

	@FindBy(id = "logo")
	private lateinit var logo: WebElement

	@FindBy(css = "#navigation .icon-v-card")
	private lateinit var infoLink: WebElement

	override fun open() {
		check(!::logo.isInitialized) { "Already initialized" }
		driver.get("http://www.twisterrob.net/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		assertEquals("http://www.twisterrob.net/", driver.currentUrl)
		// TODO dashes?
		assertEquals("TWiStErRob – Professional & Hobby Development site", driver.title)
		assertEquals("TWiStErRob — Professional & Hobby Development site", logo.getAttribute("title"))
	}

	fun gotoAbout(): AboutPage {
		infoLink.click()
		return driver.createPage()
	}
}
