package com.example.app.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(val usernameError: Int? = null,
                          val passwordError: Int? = null,
                          val nameError: Int? = null,
                          val last_nameError: Int? = null,
                          val isDataValid: Boolean = false)