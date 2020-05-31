package com.realm.todoappfullsync.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.realm.todoappfullsync.R
import com.realm.todoappfullsync.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.createAccount.setOnClickListener {
            doLogin(true)
        }

        binding.signIn.setOnClickListener {
            doLogin(false)
        }

        //create and observe View Model
        
    }

    private fun doLogin(createUser: Boolean) {

    }
}
