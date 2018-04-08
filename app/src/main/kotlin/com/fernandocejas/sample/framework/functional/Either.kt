package com.fernandocejas.sample.framework.functional

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
            when (this) {
                is Either.Left -> fnL(a)
                is Either.Right -> fnR(b)
            }
}
