package com.fernandocejas.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
    }

    fun addFragment(savedInstanceState: Bundle?, fragment: BaseFragment) {
        savedInstanceState ?:
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit()
    }
}
