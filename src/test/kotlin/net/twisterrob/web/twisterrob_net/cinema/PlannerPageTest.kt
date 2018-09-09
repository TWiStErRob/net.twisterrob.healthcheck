package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.web.test.SmokeTest
import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.openTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class PlannerPageTest : TestBase() {

	@Tags(Tag("just-open"))
	@TestFactory fun openTest() = openTest<PlannerPage>(driver)

	@SmokeTest
	@Tags(Tag("content"))
	@Test fun `Cineworld planner loads some data`() {
		val page = PlannerPage(driver)

		page.open()

		page.assertHasSomeCinemas()
		page.assertHasSomeFilms()
	}
}
