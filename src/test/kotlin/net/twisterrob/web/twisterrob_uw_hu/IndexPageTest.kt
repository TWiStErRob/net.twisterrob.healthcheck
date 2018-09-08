package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.web.test.TestBase
import org.junit.Test

class IndexPageTest : TestBase() {

	@Test fun `home page can start up`() {
		val page = IndexPage(driver)

		page.open()

		page.assertOpened()
	}

	@Test fun `home page can launch my page`() {
		val page = IndexPage(driver)
		page.open()

		val myPage = page.gotoMyPage()

		myPage.assertOpened()
	}
}
