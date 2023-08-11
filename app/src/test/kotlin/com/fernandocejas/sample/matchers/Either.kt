package com.fernandocejas.sample.matchers

import com.fernandocejas.sample.core.functional.Either
import io.kotest.assertions.fail
import io.kotest.matchers.shouldBe


infix fun <L, R> Either<L, R>.shouldBeRight(expected: R): Either<L, R> = apply {
    fold(
        { fail("Expected a Right but got $this") },
        { right -> right shouldBe expected }
    )
    return this
}

infix fun <L, R, P : (R) -> Unit> Either<L, R>.shouldBeRight(p: P): R =
    fold(
        { fail("Expected a Right but got $this") },
        { right -> p(right); right }
    )

infix fun <L, R> Either<L, R>.shouldBeLeft(expected: L): Either<L, R> = apply {
    fold(
        { left -> left shouldBe expected },
        { fail("Expected a Left but got $this") }
    )
}

infix fun <L, R, P : (L) -> Unit> Either<L, R>.shouldBeLeft(assertion: P): Either<L, R> = apply {
    fold(
        { left -> assertion(left) },
        { fail("Expected a Left but got $this") }
    )
}
