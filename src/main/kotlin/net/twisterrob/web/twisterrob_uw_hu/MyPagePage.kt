package net.twisterrob.web.twisterrob_uw_hu

import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MyPagePage(
	driver: WebDriver
) : BaseUltraWebPage(driver, framed = false) {

	@FindBy(css = "h2")
	private lateinit var header: WebElement

	override fun open() {
		launch("http://twisterrob.uw.hu/mypage/index.php")
	}

	override fun assertOpened() {
		assertThat(driver.currentUrl).isEqualTo("http://twisterrob.uw.hu/mypage/index.php")
		assertThat(driver.title).isEqualTo("Papp Róbert - Személyes HonLapja")
		assertThat(header.text).isEqualTo("Frissítések:")
	}
}
