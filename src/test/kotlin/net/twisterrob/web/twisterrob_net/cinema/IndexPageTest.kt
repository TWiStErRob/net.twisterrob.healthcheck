package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.selenium.createPage
import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.WebDriverExtension
import net.twisterrob.web.test.openTest
import net.twisterrob.web.test.tags.justOpen
import net.twisterrob.web.test.tags.navigation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver

@ExtendWith(WebDriverExtension::class)
class IndexPageTest {

	@Order(0)
	@Tags(Tag(justOpen))
	@TestFactory fun openTest(driver: WebDriver) = openTest<IndexPage>(driver)

	@SmokeTest
	@Tags(Tag(navigation))
	@Test fun `redirects and secures from unsecure url`(driver: WebDriver) {
		@Suppress("HttpUrlsUsage")
		driver.get("http://cinema.twisterrob.net")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}
}
