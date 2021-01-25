package com.orbital.sonic.mvvmarchitecture

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(): ViewModel() {

    var loginStatus = MutableLiveData<String>()


    fun doLogin(userName: String, password: String) {
        val userModel = UserModel(userName, password)
        val isValidityOk: Boolean = userModel.checkUserValidity(userName, password)

        Handler(Looper.getMainLooper()).postDelayed({
           val msg = if (isValidityOk) {
                "Login Successful"
            } else {
                "Login Fail"
            }

            loginStatus.postValue(msg)
        }, 5000)
    }
}