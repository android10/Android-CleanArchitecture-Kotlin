package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesRepositoryTest : UnitTest() {

    private lateinit var moviesRepositoryDataSource: MoviesRepository.Source

    @Mock private lateinit var dataSourceFactory: MoviesDataSource.Factory

    @Before fun setUp() {
        moviesRepositoryDataSource = MoviesRepository.Source(dataSourceFactory)
        given { dataSourceFactory.network() } .willReturn(mock(MoviesDataSource.Network::class))
    }

    @Test fun `should get movies from network`() {
        moviesRepositoryDataSource.movies()

        verify(dataSourceFactory).network()
    }
}
