package net.twisterrob.web.twisterrob_net

import net.twisterrob.web.test.TestBase
import org.junit.Test

class AboutPageTest : TestBase() {

	@Test fun `about page can start up`() {
		val about = AboutPage(driver)

		about.launch()

		about.assertLaunched()
	}

	@Test fun `about page has image of me`() {
		val about = AboutPage(driver)

		about.launch()

		about.assertImageVisible()
	}
}
