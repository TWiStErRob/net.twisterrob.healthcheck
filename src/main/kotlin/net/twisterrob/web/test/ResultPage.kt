package net.twisterrob.web.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ResultPage(driver: WebDriver) {
	init {
		PageFactory.initElements(driver, this)
	}

	@FindBy(xpath = "#result-count")
	private lateinit var numResult: WebElement

	@FindBy(xpath = ".//*[@id='results']/ol/li/ol/li/div/div/div[2]/h3/a")
	private lateinit var videos: List<WebElement>

	@FindBy(xpath = ".//*[@id='eow-title']")
	private lateinit var titleVideo: WebElement

	@FindBy(xpath = ".//*[@id='watch7-user-header']/div/a")
	private lateinit var channel: WebElement

	fun isPageOpened() =
		numResult.text.contains("About")

	fun selectVideo(selectVideo: String) =
		videos.find { it.text.contains(selectVideo) }?.click()

	fun playingVideo(titleVideo: String, channel: String) =
		this.titleVideo.text == titleVideo && this.channel.text == channel
}
