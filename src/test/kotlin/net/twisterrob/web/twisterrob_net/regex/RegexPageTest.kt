package net.twisterrob.web.twisterrob_net.regex

import net.twisterrob.web.test.SimplePageTestBase
import org.junit.Test

class RegexPageTest : SimplePageTestBase(RegexPage::class) {

	@Test fun `preset data is filled in`() {
		val page = RegexPage(driver)

		page.open()

		page.assertSearch().text().isEqualTo("a(b+)a(b*)a")
		page.assertReplace().text().isEqualTo("$0")
		page.assertInput().text().isNotBlank()
		page.assertOutput().text().isEmpty()
		page.assertDebug().isNotDisplayed()
	}

	@Test fun `test button executes`() {
		val page = RegexPage(driver)
		page.open()

		page.test()

		page.assertSearch().text().isEqualTo("a(b+)a(b*)a")
		page.assertReplace().text().isEqualTo("$0")
		page.assertInput().text().isNotBlank()
		page.assertOutput().text().isNotBlank()
		page.assertDebug().isDisplayed()
	}
}
