package net.twisterrob.web.twisterrob_net

import net.twisterrob.web.test.TestBase
import org.junit.Test

class HomePageTest : TestBase() {

	@Test fun `home page can start up`() {
		val home = HomePage(driver)

		home.launch()

		home.assertLaunched()
	}

	@Test fun `home page can launch about`() {
		val home = HomePage(driver)
		home.launch()

		val about = home.gotoAbout()

		about.assertLaunched()
	}
}
