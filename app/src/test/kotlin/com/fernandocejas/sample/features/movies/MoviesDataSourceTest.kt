package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.features.movies.MoviesDataSource.Network
import com.fernandocejas.sample.framework.network.RestApi
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesDataSourceTest : UnitTest() {

    private val MOVIE_ID = 1
    private val MOVIE_POSTER = "poster_url"

    private lateinit var network: Network

    @Mock private lateinit var restApi: RestApi

    @Before fun setUp() {
        network = Network(restApi)
        given { restApi.movies() }.willReturn(createMovieEntitiesList())
    }

    @Test fun `should get data from rest api`() {
        val testObserver = network.movies().test()

        val movie = testObserver.values()[0][0]
        with(movie) {
            id shouldEqual MOVIE_ID
            poster shouldEqual MOVIE_POSTER
        }

        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        verify(restApi).movies()
    }

    fun createMovieEntitiesList(): Single<List<MovieEntity>> {
        val movieEntity = MovieEntity(MOVIE_ID, MOVIE_POSTER)
        return Single.just(listOf(movieEntity))
    }
}
