package net.twisterrob.web.test

object Options {

	val headless: Boolean
		get() = System.getProperty("net.twisterrob.test.selenium.headless", "false").toBooleanStrict()
}
