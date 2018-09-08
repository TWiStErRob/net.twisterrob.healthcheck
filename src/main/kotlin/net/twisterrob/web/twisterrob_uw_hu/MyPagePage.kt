package net.twisterrob.web.twisterrob_uw_hu

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.test.assertEquals

class MyPagePage(driver: WebDriver) : BaseUltraWebPage(driver, framed = false) {

	@FindBy(css = "h2")
	private lateinit var header: WebElement

	override fun open() {
		launch("http://twisterrob.uw.hu/mypage/index.php")
	}

	override fun assertOpened() {
		assertEquals("Papp Róbert - Személyes HonLapja", driver.title)
		assertEquals("Frissítések:", header.text)
	}
}
