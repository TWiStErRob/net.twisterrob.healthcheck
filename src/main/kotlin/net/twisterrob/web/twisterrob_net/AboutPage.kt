package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class AboutPage(
	driver: WebDriver
) : BasePage(driver) {

	@FindBy(css = "article h1")
	private lateinit var heading: WebElement

	@FindBy(css = "img[alt='picture of me']")
	@JvmSuppressWildcards
	private lateinit var images: List<WebElement>

	override fun open() {
		check(!::heading.isInitialized) { "Already initialized" }
		driver.get("https://www.twisterrob.net/info/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		assertThat(driver.currentUrl).isEqualTo("https://www.twisterrob.net/info/")
		assertThat(driver.title).isEqualTo("About")
		assertThat(heading.text).isEqualTo("About")
	}

	fun assertImageVisible() {
		assertThat(images).anyMatch { it.isDisplayed }
	}
}
