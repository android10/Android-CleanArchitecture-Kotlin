/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.movies.ui

import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.fernandocejas.sample.core.extension.cancelTransition


class MovieDetailsAnimator {

    internal fun postponeEnterTransition(activity: FragmentActivity) =
        activity.postponeEnterTransition()

    internal fun cancelTransition(view: View) = view.cancelTransition()

    internal fun scaleUpView(view: View) =
        scaleView(view, SCALE_UP_VALUE, SCALE_UP_VALUE, SCALE_UP_DURATION)

    internal fun scaleDownView(view: View) =
        scaleView(view, SCALE_DOWN_VALUE, SCALE_DOWN_VALUE, SCALE_DOWN_DURATION)

    internal fun fadeVisible(viewContainer: ViewGroup, view: View) =
        beginTransitionFor(viewContainer, view, View.VISIBLE)

    internal fun fadeInvisible(viewContainer: ViewGroup, view: View) =
        beginTransitionFor(viewContainer, view, View.INVISIBLE)

    private fun scaleView(view: View, x: Float, y: Float, duration: Long) =
        view.animate()
            .scaleX(x)
            .scaleY(y)
            .setDuration(duration)
            .setInterpolator(FastOutSlowInInterpolator())
            .withLayer()
            .setListener(null)
            .start()

    private fun beginTransitionFor(viewContainer: ViewGroup, view: View, visibility: Int) {
        val transition = Fade()
        transition.startDelay = TRANSITION_DELAY
        transition.duration = TRANSITION_DURATION
        TransitionManager.beginDelayedTransition(viewContainer, transition)
        view.visibility = visibility
    }

    companion object {
        private const val TRANSITION_DELAY = 200L
        private const val TRANSITION_DURATION = 400L

        private const val SCALE_UP_VALUE = 1.0F
        private const val SCALE_UP_DURATION = 400L

        private const val SCALE_DOWN_VALUE = 0.0F
        private const val SCALE_DOWN_DURATION = 200L
    }
}


