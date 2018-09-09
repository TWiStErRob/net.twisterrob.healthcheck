package net.twisterrob.web.twisterrob_net.regex

import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.openTest
import net.twisterrob.web.test.tags.interaction
import net.twisterrob.web.test.tags.justOpen
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class RegexPageTest : TestBase() {

	@Tags(Tag(justOpen))
	@TestFactory fun openTest() = openTest<RegexPage>(driver)

	@Tags(Tag(justOpen))
	@Test fun `preset data is filled in`() {
		val page = RegexPage(driver)

		page.open()

		page.assertSearch().text().isEqualTo("a(b+)a(b*)a")
		page.assertReplace().text().isEqualTo("$0")
		page.assertInput().text().isNotBlank()
		page.assertOutput().text().isEmpty()
		page.assertDebug().isNotDisplayed()
	}

	@SmokeTest
	@Tags(Tag(interaction))
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

	@Tags(Tag(interaction))
	@Test fun `Java example changes form`() {
		val page = RegexPage(driver)
		page.open()

		page.showExampleJava()

		page.assertSearch().text().contains("public")
		page.assertReplace().text().contains("return")
		page.assertInput().text().contains("boolean add(T t);")
		page.assertOutput().text().contains("add[T t]: boolean;")
		page.assertDebug().isDisplayed()
	}
}
