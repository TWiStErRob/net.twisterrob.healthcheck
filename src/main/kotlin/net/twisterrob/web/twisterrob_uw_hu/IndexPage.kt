package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.selenium.createPage
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class IndexPage(driver: WebDriver) : BaseUltraWebPage(driver) {

	@FindBy(css = "a[href='/mypage/index.php']")
	private lateinit var myPageLink: WebElement

	override fun open() {
		launch("http://twisterrob.uw.hu/")
	}

	override fun assertOpened() {
		assertThat(driver.title).isEqualTo("Papp Róbert (TWiStErRob) személyes oldal")
		assertThat(myPageLink.text).isEqualTo("Saját oldal (Sok hasznos dologgal:)")
	}

	fun gotoMyPage(): MyPagePage {
		myPageLink.click()
		return driver.createPage()
	}
}
