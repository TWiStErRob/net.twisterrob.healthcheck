package net.twisterrob.selenium

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AbstractStringAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.CheckReturnValue
import org.openqa.selenium.WebElement
import kotlin.reflect.KFunction1

class WebElementAssert(element: WebElement) :
	AbstractAssert<WebElementAssert, WebElement>(element, WebElementAssert::class.java) {

	fun isSelected(): WebElementAssert = check(WebElement::isSelected, "selected")
	fun isNotSelected(): WebElementAssert = check(WebElement::isSelected, "selected")
	fun isDisplayed(): WebElementAssert = check(WebElement::isDisplayed, "displayed")
	fun isNotDisplayed(): WebElementAssert = checkNot(WebElement::isDisplayed, "displayed")
	fun isEnabled(): WebElementAssert = checkNot(WebElement::isEnabled, "enabled")
	fun isNotEnabled(): WebElementAssert = checkNot(WebElement::isEnabled, "enabled")

	fun isButton(): WebElementAssert = apply {
		val isButton = actual.tagName.equals("button", ignoreCase = true)
		val isInputButton = actual.getAttribute("type").equals("button", ignoreCase = true)
		if (!(isButton || isInputButton)) {
			failWithMessage("Expected element to be a button. But was not!")
		}
	}

	fun isLink(): WebElementAssert = apply {
		if (!actual.tagName.equals("a", ignoreCase = true)) {
			failWithMessage("Expected element to be a link. But was not!")
		}
	}

	fun hasAttributeValue(attr: String, value: String): WebElementAssert = apply {
		if (actual.getAttribute(attr) != value) {
			failWithMessage("Expected element to have attr <%s> value as <%s>. But was not!", attr, value)
		}
	}

	@CheckReturnValue
	fun text(): AbstractStringAssert<*> = assertThat(
		when (actual.tagName) {
			"textarea" -> actual.getAttribute("value")
			else -> actual.text
		}
	)

	private fun check(property: KFunction1<WebElement, Boolean>, propertyName: String) = apply {
		if (!property(actual)) {
			failWithMessage("Expected element to be $propertyName. But was not!")
		}
	}

	private fun checkNot(property: KFunction1<WebElement, Boolean>, propertyName: String) = apply {
		if (property(actual)) {
			failWithMessage("Expected element to not be $propertyName. But was!")
		}
	}

	private fun apply(block: WebElementAssert.() -> Unit): WebElementAssert {
		isNotNull
		block()
		return this
	}
}

@CheckReturnValue
fun assertThat(element: WebElement): WebElementAssert = WebElementAssert(element)
