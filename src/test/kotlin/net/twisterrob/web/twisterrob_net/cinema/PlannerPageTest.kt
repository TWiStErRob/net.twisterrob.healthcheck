package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.web.test.TestBase
import net.twisterrob.web.test.openTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class PlannerPageTest : TestBase() {

	@TestFactory fun openTest() = openTest<PlannerPage>(driver)

	@Test fun `Cineworld planner loads some data`() {
		val page = PlannerPage(driver)

		page.open()

		page.assertHasSomeCinemas()
		page.assertHasSomeFilms()
	}
}
