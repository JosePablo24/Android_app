package com.example.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.app.data.LoginRepository
import com.example.app.data.Result

import com.example.app.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String, name: String, lastname: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password, name, lastname)

        if (result is Result.Success) {
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String, name: String, lastname: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else if (!isNameValid(name)) {
            _loginForm.value = LoginFormState(nameError = R.string.invalid_password)
        } else if (!isLast_NameValid(lastname)) {
            _loginForm.value = LoginFormState(last_nameError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 10
    }

    // A placeholder Name validation check
    private fun isNameValid(name: String): Boolean {
        return name.length > 10
    }

    // A placeholder password validation check
    private fun isLast_NameValid(lastname: String): Boolean {
        return lastname.length > 10
    }
}