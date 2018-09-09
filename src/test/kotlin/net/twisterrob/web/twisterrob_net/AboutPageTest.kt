package net.twisterrob.web.twisterrob_net

import net.twisterrob.web.test.TestBase
import org.junit.jupiter.api.Test

class AboutPageTest : TestBase() {

	@Test fun `about page can start up`() {
		val page = AboutPage(driver)

		page.open()

		page.assertOpened()
	}

	@Test fun `about page has image of me`() {
		val page = AboutPage(driver)

		page.open()

		page.assertImageVisible()
	}
}
