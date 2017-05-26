package com.fernandocejas.sample.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fernandocejas.sample.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(savedInstanceState, MainFragment())
    }

    companion object {
        @JvmStatic fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
