package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.features.movies.MoviesDataSource.Network
import com.fernandocejas.sample.framework.network.RestApi
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesDataSourceNetworkTest : UnitTest() {
    private val MOVIE_ID = 1
    private val MOVIE_TITLE = "IronMan"
    private val MOVIE_YEAR = 1998
    private val MOVIE_POSTER = "url"
    private val MOVIE_COLOR = "red"

    private lateinit var network: Network

    @Mock private lateinit var restApi: RestApi

    @Before fun setUp() {
        network = Network(restApi)
        given { restApi.movies() } .willReturn(createMovieEntitiesList())
    }

    @Test fun shouldGetDataFromRestApi() {
        val testObserver = network.movies().test()

        val movie = testObserver.values()[0][0]
        with(movie) {
            id shouldEqual MOVIE_ID
            title shouldEqual MOVIE_TITLE
            year shouldEqual MOVIE_YEAR
            poster shouldEqual MOVIE_POSTER
            color shouldEqual MOVIE_COLOR
        }

        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        verify(restApi).movies()
    }

    fun createMovieEntitiesList(): Observable<List<MovieEntity>> {
        val movieEntity = MovieEntity(MOVIE_ID, MOVIE_TITLE, MOVIE_YEAR, MOVIE_POSTER, MOVIE_COLOR)
        return Observable.just(listOf(movieEntity))
    }
}
