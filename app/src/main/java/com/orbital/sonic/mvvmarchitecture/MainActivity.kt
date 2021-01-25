package com.orbital.sonic.mvvmarchitecture

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private lateinit var editUserName: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        btnLogin.setOnClickListener {
            loginResult()
        }

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.loginStatus.observe(this, { str ->
            progressBar.visibility = View.GONE
            Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
        })

    }

    private fun initViews(){
        editUserName = findViewById(R.id.et_name)
        editPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.bt_submit)
        progressBar = findViewById(R.id.progress_login)
        progressBar.visibility = View.GONE
    }

    private fun loginResult() {
        progressBar.visibility = View.VISIBLE
        val userName = editUserName.text.toString()
        val password = editPassword.text.toString()
        loginViewModel.doLogin(userName, password)
    }
}