package net.twisterrob.web.twisterrob_net.regex

import net.twisterrob.selenium.createPage
import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.WebDriverExtension
import net.twisterrob.web.test.openTest
import net.twisterrob.web.test.tags
import net.twisterrob.web.test.tags.interaction
import net.twisterrob.web.test.tags.justOpen
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver

@ExtendWith(WebDriverExtension::class)
class RegexPageTest {

	@Order(0)
	@Tags(Tag(justOpen))
	@TestFactory fun openTest(driver: WebDriver) = openTest<RegexPage>(driver)

	@Tags(Tag(tags.navigation))
	@Test fun `redirects and secures from unsecure url`(driver: WebDriver) {
		@Suppress("HttpUrlsUsage")
		driver.get("http://regex.twisterrob.net/")

		val page: RegexPage = driver.createPage()

		page.assertOpened()
	}

	@Tags(Tag(justOpen))
	@Test fun `preset data is filled in`(driver: WebDriver) {
		val page = RegexPage(driver)

		page.open()

		page.assertSearch().text().isEqualTo("a(b+)a(b*)a")
		page.assertReplace().text().isEqualTo("$0")
		page.assertInput().text().isNotBlank()
		page.assertOutput().text().isEmpty()
		page.assertDebug().isNotDisplayed()
	}

	@SmokeTest
	@Tags(Tag(interaction))
	@Test fun `test button executes`(driver: WebDriver) {
		val page = RegexPage(driver)
		page.open()

		page.test()

		page.assertSearch().text().isEqualTo("a(b+)a(b*)a")
		page.assertReplace().text().isEqualTo("$0")
		page.assertInput().text().isNotBlank()
		page.assertOutput().text().isNotBlank()
		page.assertDebug().isDisplayed()
	}

	@Tags(Tag(interaction))
	@Test fun `Java example changes form`(driver: WebDriver) {
		val page = RegexPage(driver)
		page.open()

		page.showExampleJava()

		page.assertSearch().text().contains("public")
		page.assertReplace().text().contains("return")
		page.assertInput().text().contains("boolean add(T t);")
		page.assertOutput().text().contains("add[T t]: boolean;")
		page.assertDebug().isDisplayed()
	}
}
