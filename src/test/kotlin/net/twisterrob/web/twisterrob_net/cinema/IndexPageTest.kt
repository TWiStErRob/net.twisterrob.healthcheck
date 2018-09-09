package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.openTest
import net.twisterrob.web.test.tags.justOpen
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.TestFactory

class IndexPageTest : TestBase() {

	@Tags(Tag(justOpen))
	@TestFactory fun openTest() = openTest<IndexPage>(driver)
}
