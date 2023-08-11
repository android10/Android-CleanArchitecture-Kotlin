/**
 * Copyright (C) 2019 Fernando Cejas Open Source Project
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
@file:Suppress("DEPRECATION")

package com.fernandocejas.sample.core.functional

import com.fernandocejas.sample.core.functional.Either.Left
import com.fernandocejas.sample.core.functional.Either.Right

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {

    /**
     * Represents the left side of [Either] class which by convention
     * is a "Failure".
     */
    data class Left<out L>
    @Deprecated(".toLeft()", ReplaceWith("a.toLeft()"))
    constructor(val left: L) : Either<L, Nothing>()

    /**
     * Represents the right side of [Either] class which by convention
     * is a "Success".
     */
    data class Right<out R>
    @Deprecated(".toRight()", ReplaceWith("b.toRight()"))
    constructor(val right: R) : Either<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left<L>

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     * @see Left
     * @see Right
     */
    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     *
     * Kotlin Coroutines Support.
     * @see Left
     * @see Right
     */
    suspend fun <T> coFold(fnL: suspend (L) -> T, fnR: suspend (R) -> T): T =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }

    companion object {
        /**
         * Transforms a try/catch in an Either<Exception, Right>
         * See [mapException]
         * **/
        @Suppress("TooGenericExceptionCaught")
        suspend fun <Right> catch(
            operation: suspend () -> Right
        ): Either<Exception, Right> =
            try {
                operation().toRight()
            } catch (e: Exception) {
                e.toLeft()
            }
    }
}

/** If Left is of type Exception, allows to map it to another type.
 * Rethrow the exception if [operation] returns null **/
fun <Left : Any, Right> Either<Exception, Right>.mapException(
    operation: (Exception) -> Left?
): Either<Left, Right> = when (this) {
    is Either.Left -> operation(left)?.toLeft() ?: throw left
    is Either.Right -> this
}

/** Represents the right side of [Either] class which by convention is a "Success". **/
fun <T> T.toRight(): Right<T> =
    Right(this)

/** Represents the left side of [Either] class which by convention is a "Failure". */
fun <T> T.toLeft(): Left<T> =
    Left(this)

/**
 * Composes 2 functions
 * See <a href="https://proandroiddev.com/kotlins-nothing-type-946de7d464fb">Credits to Alex Hart.</a>
 */
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Left -> Left(left)
        is Right -> fn(right)
    }

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 * It works with Kotlin Coroutines (suspension functions).
 */
suspend fun <T, L, R> Either<L, R>.coFlatMap(fn: suspend (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Left -> Left(left)
        is Right -> fn(right)
    }

/**
 * Left-biased onFailure() FP convention dictates that when this class is Left, it'll perform
 * the onFailure functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
infix fun <L, R> Either<L, R>.onFailure(fn: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Left) fn(left) }

/**
 * Right-biased onSuccess() FP convention dictates that when this class is Right, it'll perform
 * the onSuccess functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
infix fun <L, R> Either<L, R>.onSuccess(fn: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Right) fn(right) }

/**
 * Right-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <L, R, T> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> =
    when (this) {
        is Left -> Left(left)
        is Right -> Right(fn(right))
    }

/**
 * Right-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 * It works with Kotlin Coroutines (suspension functions).
 */
suspend fun <L, R, T> Either<L, R>.coMap(fn: suspend (R) -> (T)): Either<L, T> =
    when (this) {
        is Left -> Left(left)
        is Right -> Right(fn(right))
    }

/**
 * Returns the value from this `Right` or the given argument if this is a `Left`.
 * Right(12).getOrElse(17) RETURNS 12 and Left(12).getOrElse(17) RETURNS 17
 */
fun <L, R> Either<L, R>.getOrElse(value: R): R =
    when (this) {
        is Left -> value
        is Right -> right
    }
