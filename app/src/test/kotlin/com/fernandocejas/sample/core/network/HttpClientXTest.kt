package com.fernandocejas.sample.core.network

import io.kotest.matchers.equals.shouldBeEqual
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Test

class HttpClientXTest {

    @Test
    fun `map to ApiResponse Success when client returns OK`() = runTest {
        val tested = FakeApi(httpClient(okEngine))

        val expected = ApiResponse.Success(FakeApi.FakeData())
        val actual = tested.endpoint()

        actual shouldBeEqual expected
    }

    @Test
    fun `map to ApiResponse HttpError with code when client returns 40x`() = runTest {
        val tested = FakeApi(httpClient(badRequestEngine))

        val expected = ApiResponse.Error.HttpError(400, FakeApi.FakeError("bad request"))
        val actual = tested.endpoint()

        actual shouldBeEqual expected
    }

    @Test
    fun `map to ApiResponse HttpError with code and body when client returns 50x`() = runTest {
        val tested = FakeApi(httpClient(serverErrorEngine))

        val expected = ApiResponse.Error.HttpError(500, FakeApi.FakeError("internal server error"))
        val actual = tested.endpoint()

        actual shouldBeEqual expected
    }

    @Test
    fun `map to ApiResponse HttpError with code and null body when client returns 50x and error doesn't match contract`() =
        runTest {
            val tested = FakeApi(httpClient(malformedServerErrorEngine))

            val expected = ApiResponse.Error.HttpError(500, null)
            val actual = tested.endpoint()

            actual shouldBeEqual expected
        }

    @Test
    fun `map to ApiResponse NetworkError when there is no internet`() = runTest {
        val tested = FakeApi(httpClient(networkErrorEngine))

        val expected = ApiResponse.Error.NetworkError
        val actual = tested.endpoint()

        actual shouldBeEqual expected
    }

    @Test
    fun `map to ApiResponse SerialisationError when data format doesn't match`() = runTest {
        val tested = FakeApi(httpClient(serialisationErrorEngine))

        val expected = ApiResponse.Error.SerializationError
        val actual = tested.endpoint()

        actual shouldBeEqual expected
    }

    private fun httpClient(engine: MockEngine) = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    private class FakeApi(
        private val client: HttpClient,
    ) {

        suspend fun endpoint(): ApiResponse<FakeData, FakeError> =
            client.safeRequest {
                url("https://movies.com/api")
                contentType(ContentType.Application.Json)
            }

        @Serializable
        data class FakeData(
            val id: Int = 1,
            val name: String = "Movie",
        )

        @Serializable
        data class FakeError(
            val message: String
        )
    }

    private val headers = headersOf(HttpHeaders.ContentType, "application/json")

    private val serialisationErrorEngine = MockEngine {
        respond(
            content = "just a string",
            status = HttpStatusCode.OK,
            headers = headers,
        )
    }

    private val badRequestEngine = MockEngine {
        respond(
            content = """
        {
            "message": "bad request"
        }
            """.trimIndent(),
            status = HttpStatusCode.BadRequest,
            headers = headers,
        )
    }

    private val serverErrorEngine = MockEngine {
        respond(
            content = """
        {
            "message": "internal server error"
        }
            """.trimIndent(),
            status = HttpStatusCode.InternalServerError,
            headers = headers,
        )
    }

    private val malformedServerErrorEngine = MockEngine {
        respond(
            content = """
        {
            "incorrect_field_name": "error"
        }
            """.trimIndent(),
            status = HttpStatusCode.InternalServerError,
            headers = headers,
        )
    }

    private val networkErrorEngine = MockEngine { throw IOException("No internet") }

    private val okEngine = MockEngine {
        respond(
            content = """
        {
            "id": 1,
            "name": "Movie"
        }
            """.trimIndent(),
            status = HttpStatusCode.OK,
            headers = headers,
        )
    }
}
