/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
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
package com.fernandocejas.sample.core.interactor

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.core.exception.Failure
import com.fernandocejas.sample.core.functional.Either
import com.fernandocejas.sample.core.functional.Either.Right
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class UseCaseTest : AndroidTest() {

    private val TYPE_TEST = "Test"
    private val TYPE_PARAM = "ParamTest"

    private lateinit var useCase: UseCase<MyType, MyParams>

    @Before
    fun setUp() {
        useCase = mock { onBlocking { run(any()) } doReturn Right(MyType(TYPE_TEST)) }
    }

    @Test fun `running use case should return 'Either' of use case type`() {
        val params = MyParams(TYPE_PARAM)
        val result = runBlocking { useCase.run(params) }

        result shouldEqual Right(MyType(TYPE_TEST))
    }

    @Test fun `should return correct data when executing use case`() {
        var result: Either<Failure, MyType>? = null

        val params = MyParams("TestParam")
        val onResult = { myResult: Either<Failure, MyType> -> result = myResult }

        whenever(runBlocking { useCase(params, onResult) })
                .then { result shouldEqual Right(MyType(TYPE_TEST)) }
    }

    data class MyType(val name: String)
    data class MyParams(val name: String)

}
