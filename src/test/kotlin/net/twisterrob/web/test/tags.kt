package net.twisterrob.web.test

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

@Suppress("DEPRECATION")
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tags(Tag(tags.smoke))
annotation class SmokeTest

@Suppress("ClassName") // simulate package
object tags {

	/**
	 * Smoke tests to be run to check that all websites and services are up and running.
	 */
	@Deprecated("Use the annotation", ReplaceWith("@SmokeTest"))
	const val smoke = "smoke"

	/**
	 * Just opens a page and check if it opened. Simple and quick. Good for warming up cloud servers.
	 */
	const val justOpen = "just-open"

	/**
	 * Navigation happens in this test, meaning one page opens another.
	 */
	const val navigation = "navigation"

	/**
	 * Interaction happens in this test, meaning the test emulates user behavior.
	 */
	const val interaction = "interaction"

	/**
	 * Content verification happens in this test, meaning the test check for existence of user-readable text.
	 */
	const val content = "content"
}
