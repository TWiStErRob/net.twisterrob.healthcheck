package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.createPage
import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class IndexPage(
	driver: WebDriver
) : BasePage(driver) {

	@FindBy(id = "logo")
	private lateinit var logo: WebElement

	@FindBy(css = "#navigation .icon-v-card")
	private lateinit var infoLink: WebElement

	override fun open() {
		check(!::logo.isInitialized) { "Already initialized" }
		driver.get("https://www.twisterrob.net/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		assertThat(driver.currentUrl).isEqualTo("https://www.twisterrob.net/")
		assertTitle()
	}

	private fun assertTitle() {
		// TODO dashes?
		assertThat(driver.title).isEqualTo("TWiStErRob – Professional & Hobby Development site")
		assertThat(logo.getAttribute("title")).isEqualTo("TWiStErRob — Professional & Hobby Development site")
	}

	fun gotoAbout(): AboutPage {
		infoLink.click()
		return driver.createPage()
	}
}
