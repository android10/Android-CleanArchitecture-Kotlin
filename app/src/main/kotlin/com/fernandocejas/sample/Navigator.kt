package com.fernandocejas.sample

import android.content.Context
import com.fernandocejas.sample.features.users.UsersActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun showMainScreen(context: Context) {
        //You could check whether the user is logged in or not at this point
        //and decide where to go from here.
        //In this sample, a main screen is showed
        context.startActivity(UsersActivity.callingIntent(context))
    }
}

