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
package com.fernandocejas.sample.core.functional

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.core.failure.Failure
import com.fernandocejas.sample.matchers.shouldBeLeft
import com.fernandocejas.sample.matchers.shouldBeRight
import io.kotest.assertions.fail
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class EitherTest : UnitTest() {

    private lateinit var either: Either<Int, String>

    @Test
    fun `given fold is called, when either is Right, applies fnR and returns its result`() {
        either = Either.Right("Success")
        val result = either.fold({ io.kotest.assertions.fail("Shouldn't be executed") }) { 5 }

        result shouldBe 5
    }

    @Test
    fun `given fold is called, when either is Right, it may returns a nullable`() {
        either = Either.Right("Success")
        val result: Int? = either.fold({ io.kotest.assertions.fail("Shouldn't be executed") }) { null }
        result shouldBe null
    }

    @Test
    fun `given fold is called, when either is Left, applies fnL and returns its result`() {
        either = Either.Left(12)

        val foldResult = "Fold Result"
        val result = either.fold({ foldResult }) { io.kotest.assertions.fail("Shouldn't be executed") }

        result shouldBe foldResult
    }

    @Test
    fun `given coFold is called, when either is Right, applies fnR and returns its result`() {
        runTest {
            either = Either.Right("Success")
            val result = either.coFold({ io.kotest.assertions.fail("Shouldn't be executed") }) { "hello" }

            result shouldBe "hello"
        }
    }

    @Test
    fun `given coFold is called, when either is Left, applies fnL and returns its result`() {
        runTest {
            either = Either.Left(12)

            val foldResult = "Fold Result"
            val result = either.coFold({ foldResult }) { io.kotest.assertions.fail("Shouldn't be executed") }

            result shouldBe foldResult
        }
    }

    @Test
    fun `given flatMap is called, when either is Left, doesn't invoke function and returns original Either`() {
        either = Either.Left(12)

        val result: Either<Int, Int> = either.flatMap {
            io.kotest.assertions.fail("Shouldn't be executed")
        }

        result.isLeft shouldBe true
        result shouldBe either
    }

    @Test
    fun `given coFlatMap is called, when either is Left, doesn't invoke function and returns original Either`() {
        runTest {
            either = Either.Left(12)

            val result: Either<Int, Int> = either.coFlatMap {
                io.kotest.assertions.fail("Shouldn't be executed")
            }

            result.isLeft shouldBe true
            result shouldBe either
        }
    }

    @Test
    fun `given onFailure is called, when either is Right, doesn't invoke function and returns original Either`() {
        val success = "Success"
        either = Either.Right(success)

        val result = either.onFailure { io.kotest.assertions.fail("Shouldn't be executed") }

        result shouldBe either
        either.getOrElse("Failure") shouldBe success
    }

    @Test
    fun `given onFailure is called, when either is Left, invokes function with left value and returns original Either`() {
        either = Either.Left(12)

        var methodCalled = false
        val result = either.onFailure {
            it shouldBe 12
            methodCalled = true
        }

        result shouldBe either
        methodCalled shouldBe true
    }

    @Test
    fun `given onSuccess is called, when either is Right, invokes function with right value and returns original Either`() {
        val success = "Success"
        either = Either.Right(success)

        var methodCalled = false
        val result = either.onSuccess {
            it shouldBe success
            methodCalled = true
        }

        result shouldBe either
        methodCalled shouldBe true
    }

    @Test
    fun `given onSuccess is called, when either is Left, doesn't invoke function and returns original Either`() {
        either = Either.Left(12)

        val result = either.onSuccess {
            io.kotest.assertions.fail("Shouldn't be executed")
        }

        result shouldBe either
    }

    @Test
    fun `given map is called, when either is Right, invokes function with right value and returns a new Either`() {
        val success = "Success"
        val resultValue = "Result"
        either = Either.Right(success)

        val result = either.map {
            it shouldBe success
            resultValue
        }

        result shouldBe Either.Right(resultValue)
    }

    @Test
    fun `given map is called, when either is Left, doesn't invoke function and returns original Either`() {
        either = Either.Left(12)

        val result = either.map {
            io.kotest.assertions.fail("Shouldn't be executed")
        }

        result.isLeft shouldBe true
        result shouldBe either
    }

    @Test
    fun `given coMap is called, when either is Right, invokes function with right value and returns a new Either`() {
        runTest {
            val success = "Success"
            val resultValue = "Result"
            either = Either.Right(success)

            val result = either.coMap {
                it shouldBe success
                resultValue
            }

            result shouldBe Either.Right(resultValue)
        }
    }

    @Test
    fun `given coMap is called, when either is Left, doesn't invoke function and returns original Either`() {
        runTest {
            either = Either.Left(12)

            val result = either.coMap {
                io.kotest.assertions.fail("Shouldn't be executed")
            }

            result.isLeft shouldBe true
            result shouldBe either
        }
    }

    @Test
    fun `extension functions give you a coherent left-to-right reading order`() {
        val left = "Horrible failure".count().times(3).toLeft()
        left shouldBe Either.Left(48)

        val right = "Success!".count().times(3).toRight()
        right shouldBe Either.Right(24)
    }

    @Test
    fun `replace try catch by an either`() = runTest {
        val failure = Failure.ServerError

        Either.catch {
            delay(1)
            "42".toInt()
        } shouldBeRight 42

        Either.catch {
            delay(1)
            "abc".toInt()
        }.mapException { e: Exception ->
            failure.takeIf { e is NumberFormatException }
        } shouldBeLeft failure

        @Suppress("TooGenericExceptionThrown")
        shouldThrowAny {
            Either.catch {
                throw RuntimeException("That was unexpected")
            }.mapException { e ->
                failure.takeIf { e is NumberFormatException }
            }
        }.shouldHaveMessage("That was unexpected")
    }
}
