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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fernandocejas.sample.R

class MoviesActivity : AppCompatActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }
//
//    override fun fragment() = MoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)
    }

    fun testString() {
        var stringInput: String? = null
        // stringInput is smart-cast to String type
        stringInput = ""
        try {
            // The compiler knows that stringInput isn't null
            println(stringInput.length)
            // 0

            // The compiler rejects previous smart cast information for
            // stringInput. Now stringInput has the String? type.
            stringInput = null

            // Trigger an exception
            if (2 > 1) throw Exception()
            stringInput = ""
        } catch (exception: Exception) {
            // In Kotlin 2.0.0, the compiler knows stringInput
            // can be null, so stringInput stays nullable.
            println(stringInput?.length)
            // null

            // In Kotlin 1.9.20, the compiler says that a safe call isn't
            // needed, but this is incorrect.
        }
    }
}

