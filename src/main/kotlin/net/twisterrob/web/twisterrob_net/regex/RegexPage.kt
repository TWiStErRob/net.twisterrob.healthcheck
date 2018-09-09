package net.twisterrob.web.twisterrob_net.regex

import net.twisterrob.selenium.assertThat
import net.twisterrob.selenium.initElements
import net.twisterrob.selenium.waitForJQuery
import net.twisterrob.web.test.BasePage
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class RegexPage(driver: WebDriver) : BasePage(driver) {

	@FindBy(tagName = "h1")
	private lateinit var header: WebElement

	@FindBy(id = "search")
	private lateinit var search: WebElement
	@FindBy(id = "replace")
	private lateinit var replace: WebElement

	@FindBy(id = "input")
	private lateinit var input: WebElement
	@FindBy(id = "output")
	private lateinit var output: WebElement

	@FindBy(css = "input[value='Test']")
	private lateinit var testButton: WebElement
	@FindBy(id = "gsonDebug")
	private lateinit var debug: WebElement

	@FindBy(id = "ex-java")
	private lateinit var exampleJava: WebElement

	override fun open() {
		check(!::header.isInitialized) { "Already initialized" }
		driver.get("http://regex.twisterrob.net/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		assertThat(driver.currentUrl).startsWith("http://regex.twisterrob.net/")
		assertThat(driver.title).isEqualTo("Regular Expressions (Regex) Sandbox")
		assertThat(header).text().isEqualTo("Regular Expressions (Regex) Sandbox")
	}

	fun test() = testButton.submit().also { driver.waitForJQuery() }

	fun assertSearch() = assertThat(search)
	fun assertReplace() = assertThat(replace)
	fun assertInput() = assertThat(input)
	fun assertOutput() = assertThat(output)
	fun assertDebug() = assertThat(debug)
}

