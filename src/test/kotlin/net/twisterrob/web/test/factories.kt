package net.twisterrob.web.test

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.openqa.selenium.WebDriver

inline fun <reified T : BasePage> openTest(driver: WebDriver): Iterable<DynamicTest> = listOf(createOpenTest<T>(driver))

inline fun <reified T : BasePage> createOpenTest(driver: WebDriver): DynamicTest =
	dynamicTest("page can start up") {
		val page = T::class.constructors.single().call(driver)

		page.open()

		page.assertOpened()
	}