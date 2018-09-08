package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

abstract class BaseUltraWebPage(
	driver: WebDriver,
	private val framed: Boolean = true
) : BasePage(driver) {

	@FindBy(tagName = "html")
	private lateinit var root: WebElement

	protected fun launch(url: String) {
		check(!::root.isInitialized) { "Already initialized" }
		driver.get(url)
		if (framed) {
			driver.switchTo().frame("uwframe")
		}
		driver.initElements(this)
	}
}
