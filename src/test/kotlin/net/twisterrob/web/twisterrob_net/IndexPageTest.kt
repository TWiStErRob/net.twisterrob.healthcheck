package net.twisterrob.web.twisterrob_net

import net.twisterrob.selenium.createPage
import net.twisterrob.web.test.TestBase
import org.junit.Test

class IndexPageTest : TestBase() {

	@Test fun `home page can start up`() {
		val page = IndexPage(driver)

		page.open()

		page.assertOpened()
	}

	@Test fun `home page can launch about`() {
		val page = IndexPage(driver)
		page.open()

		val about = page.gotoAbout()

		about.assertOpened()
	}

	@Test fun `redirects from naked domain`() {
		driver.get("http://twisterrob.net")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}

	@Test fun `redirects from GitHub raw name`() {
		driver.get("http://twisterrob.github.io")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}
}
