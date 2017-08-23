@file:JvmName("AndroidAssertions")
@file:JvmMultifileClass

package com.fernandocejas.sample

import android.support.v7.app.AppCompatActivity
import org.amshove.kluent.shouldEqual
import org.robolectric.Robolectric
import org.robolectric.Shadows
import kotlin.reflect.KClass

infix fun KClass<out AppCompatActivity>.`shouldNavigateTo`(nextActivity: KClass<out AppCompatActivity>) = {
    val originActivity = Robolectric.buildActivity(this.java).get()
    val shadowActivity = Shadows.shadowOf(originActivity)
    val nextIntent = shadowActivity.peekNextStartedActivity()

    nextIntent.component.className shouldEqual nextActivity.java.canonicalName
}
