package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.web.test.SimplePageTestBase
import org.junit.jupiter.api.Test

class PlannerPageTest : SimplePageTestBase(PlannerPage::class) {

	@Test fun `Cineworld planner loads some data`() {
		val page = PlannerPage(driver)

		page.open()

		page.assertHasSomeCinemas()
		page.assertHasSomeFilms()
	}
}
