package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.tags.justOpen
import net.twisterrob.web.test.tags.navigation
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
	@Test fun `home page can launch my page`() {
		val page = IndexPage(driver)
		page.open()

		val myPage = page.gotoMyPage()

		myPage.assertOpened()
	}
}
