package com.fernandocejas.sample.framework.functional

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight: Boolean
        get() = this is Right<R>

    val isLeft: Boolean
        get() = this is Left<L>
}

fun <T, L, R> Either<L, R>.either(fnL: (L) -> T, fnR: (R) -> T): T =
        when (this) {
            is Either.Left -> fnL(a)
            is Either.Right -> fnR(b)
        }
