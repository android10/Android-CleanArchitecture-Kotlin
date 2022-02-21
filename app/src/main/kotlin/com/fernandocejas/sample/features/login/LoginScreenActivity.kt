package com.fernandocejas.sample.features.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fernandocejas.sample.R
import kotlinx.android.synthetic.main.activity_login_screen.*


class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        btn_login.setOnClickListener {
            if(editUsername.text.trim().isEmpty()||editPassword.text.trim().isEmpty()){
                Toast.makeText(this, "co", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, editUsername.text.toString(), Toast.LENGTH_LONG).show()
            }

        }

    }


}