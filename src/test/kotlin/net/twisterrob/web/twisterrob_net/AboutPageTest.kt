package net.twisterrob.web.twisterrob_net

import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.tags.content
import net.twisterrob.web.test.tags.justOpen
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class AboutPageTest : TestBase() {

	@Tags(Tag(justOpen))
	@Test fun `about page can start up`() {
		val page = AboutPage(driver)

		page.open()

		page.assertOpened()
	}

	@Tags(Tag(content))
	@Test fun `about page has image of me`() {
		val page = AboutPage(driver)

		page.open()

		page.assertImageVisible()
	}
}
