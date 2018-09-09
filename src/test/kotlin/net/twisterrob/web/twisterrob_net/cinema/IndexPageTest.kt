package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.openTest
import org.junit.jupiter.api.TestFactory

class IndexPageTest : TestBase() {

	@TestFactory fun openTest() = openTest<IndexPage>(driver)
}
