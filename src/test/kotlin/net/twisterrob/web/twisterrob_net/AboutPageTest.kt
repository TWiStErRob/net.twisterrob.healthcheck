package net.twisterrob.web.twisterrob_net

import net.twisterrob.web.test.WebDriverExtension
import net.twisterrob.web.test.tags.content
import net.twisterrob.web.test.tags.justOpen
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver

@ExtendWith(WebDriverExtension::class)
class AboutPageTest {

	@Tags(Tag(justOpen))
	@Test fun `about page can start up`(driver: WebDriver) {
		val page = AboutPage(driver)

		page.open()

		page.assertOpened()
	}

	@Tags(Tag(content))
	@Test fun `about page has image of me`(driver: WebDriver) {
		val page = AboutPage(driver)

		page.open()

		page.assertImageVisible()
	}
}
