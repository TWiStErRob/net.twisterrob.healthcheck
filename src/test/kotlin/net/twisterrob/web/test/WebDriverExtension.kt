package net.twisterrob.web.test

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.openqa.selenium.WebDriver

class WebDriverExtension : BeforeEachCallback, AfterEachCallback, ParameterResolver {

	override fun beforeEach(context: ExtensionContext) {
		context.store.put(KEY, Browser.createDriver())
	}

	override fun afterEach(context: ExtensionContext) {
		context.store.remove(KEY, WebDriver::class.java).quit()
	}

	override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean =
		parameterContext.parameter.type == WebDriver::class.java

	override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any? =
		extensionContext.store.get(KEY, WebDriver::class.java)

	companion object {

		private val ExtensionContext.store: ExtensionContext.Store
			get() = this.getStore(NAMESPACE)

		private val NAMESPACE = ExtensionContext.Namespace.create(WebDriverExtension::class.qualifiedName)

		private val KEY: Any = WebDriver::class
	}
}
