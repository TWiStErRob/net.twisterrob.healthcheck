package net.twisterrob.web.twisterrob_uw_hu

import net.twisterrob.selenium.createPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.test.assertEquals

class IndexPage(driver: WebDriver) : BaseUltraWebPage(driver) {

	@FindBy(css = "a[href='/mypage/index.php']")
	private lateinit var myPageLink: WebElement

	override fun open() {
		launch("http://twisterrob.uw.hu/")
	}

	override fun assertOpened() {
		assertEquals("Papp Róbert (TWiStErRob) személyes oldal", driver.title)
		assertEquals("Saját oldal (Sok hasznos dologgal:)", myPageLink.text)
	}

	fun gotoMyPage(): MyPagePage {
		myPageLink.click()
		return driver.createPage()
	}
}
