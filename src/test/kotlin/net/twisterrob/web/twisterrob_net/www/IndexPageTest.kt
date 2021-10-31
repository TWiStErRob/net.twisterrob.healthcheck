package net.twisterrob.web.twisterrob_net.www

import net.twisterrob.selenium.createPage
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
	@Test fun `home page can launch about`(driver: WebDriver) {
		val page = IndexPage(driver)
		page.open()

		val about = page.gotoAbout()

		about.assertOpened()
	}

	@Disabled(".htaccess is not deployed")
	@SmokeTest
	@Tags(Tag(navigation))
	@Test fun `redirects from naked domain`(driver: WebDriver) {
		driver.get("http://twisterrob.net")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}

	/**
	 * Testing https://github.blog/2018-05-01-github-pages-custom-domains-https/#configuring-your-domain.
	 *
	 * See https://github.com/TWiStErRob/twisterrob.github.io/settings/pages
	 * > *Enforce HTTPS*
	 * > HTTPS provides a layer of encryption that prevents others from snooping on or tampering with traffic to your site.
	 * > When HTTPS is enforced, your site will only be served over HTTPS.
	 */
	@Tags(Tag(navigation))
	@Test fun `redirects from unsecure GitHub raw name`(driver: WebDriver) {
		@Suppress("HttpUrlsUsage")
		driver.get("http://twisterrob.github.io")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}

	@Tags(Tag(navigation))
	@Test fun `redirects from GitHub raw name`(driver: WebDriver) {
		driver.get("https://twisterrob.github.io")

		val page: IndexPage = driver.createPage()

		page.assertOpened()
	}
}
