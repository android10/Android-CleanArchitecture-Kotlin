package com.fernandocejas.sample.features.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fernandocejas.sample.BaseActivity

class UsersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(savedInstanceState, UsersFragment())
    }

    companion object {
        @JvmStatic fun callingIntent(context: Context): Intent {
            return Intent(context, UsersActivity::class.java)
        }
    }
}
