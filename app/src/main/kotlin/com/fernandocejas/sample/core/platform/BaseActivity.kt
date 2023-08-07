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
package com.fernandocejas.sample.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fernandocejas.sample.core.extension.inTransaction
import com.fernandocejas.sample.databinding.ActivityLayoutBinding

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolBarContainer.toolbar)
        addFragment(savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    fun toolbar() = binding.toolBarContainer.toolbar

    fun fragmentContainer() = binding.fragmentContainer
    fun progressBar() = binding.toolBarContainer.progress

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(binding.fragmentContainer.id, fragment())
        }

    abstract fun fragment(): BaseFragment
}
