package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.createPage
import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.tags.justOpen
import net.twisterrob.web.test.tags.navigation
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class IndexPageTest : TestBase() {

	@SmokeTest
	@Tags(Tag(justOpen))
	@Test fun `home page can start up`() {
		val page = IndexPage(driver)

		page.open()

		page.assertOpened()
	}

	@Tags(Tag(navigation))
	@Test fun `home page can launch about`() {
		val page = IndexPage(driver)
		page.open()

		val about = page.gotoAbout()

		about.assertOpened()
	}

	@Disabled(".htaccess is not deployed")
	@SmokeTest
	@Tags(Tag(navigation))
	@Test fun `redirects from naked domain`() {
		driver.get("http://twisterrob.net")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}

	@Tags(Tag(navigation))
	@Test fun `redirects from GitHub raw name`() {
		driver.get("http://twisterrob.github.io")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}
}
