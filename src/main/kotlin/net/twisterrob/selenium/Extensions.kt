package net.twisterrob.selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

fun WebDriver.initElements(page: Any) = PageFactory.initElements(this, page)

inline fun <reified T> WebDriver.createPage(): T = PageFactory.initElements(this, T::class.java)
