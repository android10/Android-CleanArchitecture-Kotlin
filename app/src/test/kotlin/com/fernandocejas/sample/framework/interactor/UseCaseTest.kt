package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.AndroidTest
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

class UseCaseTest : AndroidTest() {

    private val TYPE_TEST = "Test"
    private val TYPE_PARAM = "ParamTest"

    private val useCase = MyUseCase()

    @Test fun `running use case should return use case type`() {
        val myParams = MyParams(TYPE_PARAM)
        val myType = runBlocking { useCase.run(myParams) }

        myType shouldEqual MyType(TYPE_TEST)
    }

    @Test fun `should return correct data when executing use case`() {
        var myTypeResult: MyType? = null

        val myParams = MyParams("TestParam")
        val onSuccess = { myType: MyType -> myTypeResult = myType }

        runBlocking { useCase.execute(onSuccess, myParams) }

        myTypeResult shouldEqual MyType(TYPE_TEST)
    }

    data class MyType(val name: String)
    data class MyParams(val name: String)

    private inner class MyUseCase : UseCase<MyType, MyParams>() {
        override suspend fun run(params: MyParams) = MyType(TYPE_TEST)
    }
}
