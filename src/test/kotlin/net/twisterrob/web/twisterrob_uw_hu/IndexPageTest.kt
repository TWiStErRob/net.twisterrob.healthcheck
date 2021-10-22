package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.WebDriverExtension
import net.twisterrob.web.test.tags.justOpen
import net.twisterrob.web.test.tags.navigation
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver

@Disabled("twisterrob.uw.hu is dead")
@ExtendWith(WebDriverExtension::class)
class IndexPageTest {

	@SmokeTest
	@Tags(Tag(justOpen))
	@Test fun `home page can start up`(driver: WebDriver) {
		val page = IndexPage(driver)

		page.open()

		page.assertOpened()
	}

	@Tags(Tag(navigation))
	@Test fun `home page can launch my page`(driver: WebDriver) {
		val page = IndexPage(driver)
		page.open()

		val myPage = page.gotoMyPage()

		myPage.assertOpened()
	}
}
