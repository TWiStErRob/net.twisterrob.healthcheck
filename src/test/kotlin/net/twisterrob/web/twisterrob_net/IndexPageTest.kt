package net.twisterrob.web.twisterrob_net

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
}
