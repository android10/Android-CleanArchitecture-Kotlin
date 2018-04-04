package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.exception.ErrorEvent.FeatureError

class MovieError {
    class ListNotAvailable: FeatureError()
    class NonExistentMovie: FeatureError()
}

