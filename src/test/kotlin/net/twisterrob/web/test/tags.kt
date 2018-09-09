package net.twisterrob.web.test

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tags(Tag("smoke"))
annotation class SmokeTest
