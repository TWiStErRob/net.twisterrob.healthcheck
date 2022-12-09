package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class IndexPage(
	driver: WebDriver
) : BasePage(driver) {

	@FindBy(css = "h1")
	private lateinit var heading: WebElement

	override fun open() {
		check(!::heading.isInitialized) { "Already initialized" }
		driver.get("https://cinema.twisterrob.net/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		assertThat(driver.currentUrl).isEqualTo("https://cinema.twisterrob.net/")
		assertThat(heading.text).isEqualTo("Cineworld Server API")
	}
}
