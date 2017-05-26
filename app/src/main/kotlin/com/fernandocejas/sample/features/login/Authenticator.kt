package com.fernandocejas.sample.features.login

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(){

    fun userLoggedIn(): Boolean {
        //Learning purpose: We assume the user is always logged in
        //Here you should put your own logic to return whether the user
        //is authenticated or not
        return true
    }
}
