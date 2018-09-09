package net.twisterrob.web.test

import org.junit.Test
import kotlin.reflect.KClass

// TODO move to JUnit 5 and create @TestFactory for this
abstract class SimplePageTestBase(
	private val simplePage: KClass<out BasePage>
) : TestBase() {

	@Test fun `page can start up`() {
		val page = simplePage.constructors.single().call(driver)

		page.open()

		page.assertOpened()
	}
}
