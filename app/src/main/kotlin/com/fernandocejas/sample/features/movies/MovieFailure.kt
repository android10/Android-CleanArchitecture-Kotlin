package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.exception.Failure.FeatureFailure

class MovieFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentMovie: FeatureFailure()
}

