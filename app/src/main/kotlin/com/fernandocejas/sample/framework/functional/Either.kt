package com.fernandocejas.sample.framework.functional

class Either<out L, out R>
private constructor(private val left: L? = null, private val right: R? = null) {

    fun <L, R> left(l: L): Either<L, R> = Either(l, null)

    fun left() = left

    fun isLeft() = left != null

    fun <L, R> right(r: R): Either<L, R> = Either(null, r)

    fun right() = right

    fun isRight() = right != null
}