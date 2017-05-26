package com.fernandocejas.sample.di

interface SubcomponentBuilder<out T> {
    fun build(): T
}
