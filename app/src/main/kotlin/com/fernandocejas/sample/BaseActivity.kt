package com.fernandocejas.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fernandocejas.sample.framework.extension.inTransaction

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState)
    }

    fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?:
                supportFragmentManager.inTransaction {
                    add(R.id.fragmentContainer, fragment())
                }
    }

    abstract fun fragment() : BaseFragment
}
