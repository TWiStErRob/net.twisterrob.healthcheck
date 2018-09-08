package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.web.test.TestBase
import org.junit.Test

class MyPagePageTest : TestBase() {

	@Test fun `my page can start up`() {
		val page = MyPagePage(driver)

		page.open()

		page.assertOpened()
	}
}
