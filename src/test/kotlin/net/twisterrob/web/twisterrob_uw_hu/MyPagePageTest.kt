package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.openTest
import org.junit.jupiter.api.TestFactory

class MyPagePageTest : TestBase() {

	@TestFactory fun openTest() = openTest<MyPagePage>(driver)
}
