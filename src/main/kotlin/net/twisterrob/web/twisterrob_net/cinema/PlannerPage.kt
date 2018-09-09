package net.twisterrob.web.twisterrob_net.cinema

import net.twisterrob.selenium.assertThat
import net.twisterrob.selenium.initElements
import net.twisterrob.web.test.BasePage
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import java.time.LocalDate

class PlannerPage(driver: WebDriver) : BasePage(driver) {

	@FindBy(id = "cineworldDate-display")
	private lateinit var dateLabel: WebElement

	@FindBy(className = "performances-loading")
	private lateinit var performancesEmpty: WebElement

	private object Cinemas {
		val london: By = cssSelector("#cinemas-list-london li")
	}

	private object Films {
		val new: By = cssSelector("#films-list li")
	}

	override fun open() {
		check(!::dateLabel.isInitialized) { "Already initialized" }
		driver.get("http://cinema.twisterrob.net/planner/")
		driver.initElements(this)
	}

	override fun assertOpened() {
		assertThat(driver.currentUrl).startsWith("http://cinema.twisterrob.net/planner/")
		assertThat(driver.title).isEqualTo("Cineworld Cinemas Planner - Developer Beta")

		// static content
		assertThat(dateLabel.text).startsWith("Selected date is: ")

		// dynamic content
		assertThat(driver.currentUrl).contains("d=${LocalDate.now().year}")
		assertThat(performancesEmpty).text().isEqualTo("Please select a film...")
	}

	fun assertHasSomeCinemas() {
		val londonCinemasList = driver.findElements(Cinemas.london)
		assertThat(londonCinemasList).size().isGreaterThan(1)
	}

	fun assertHasSomeFilms() {
		val newFilmsList = driver.findElements(Films.new)
		assertThat(newFilmsList).size().isGreaterThan(1)
	}
}
