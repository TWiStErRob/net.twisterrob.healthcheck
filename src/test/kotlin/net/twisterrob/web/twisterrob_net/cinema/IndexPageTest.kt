package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.web.test.WebDriverExtension
import net.twisterrob.web.test.openTest
import net.twisterrob.web.test.tags.justOpen
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver

@ExtendWith(WebDriverExtension::class)
class IndexPageTest {

	@Order(0)
	@Tags(Tag(justOpen))
	@TestFactory fun openTest(driver: WebDriver) = openTest<IndexPage>(driver)
}
