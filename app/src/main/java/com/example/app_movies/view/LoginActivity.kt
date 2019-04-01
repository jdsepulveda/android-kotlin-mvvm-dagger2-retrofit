package com.example.app_movies.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.app_movies.R
import com.example.app_movies.base.App
import com.example.app_movies.model.UserDataManager
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var userDataManager: UserDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //ButterKnife.bind(this)

        App.getUserComponent(this)?.inject(this)

        tvPassword.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        btnLogin.setOnClickListener{ view ->
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        tvUserName.error = null
        tvPassword.error = null

        val userName = tvUserName.text.toString()
        val password = tvPassword.text!!.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            tvPassword.error = getString(R.string.error_invalid_password)
            focusView = tvPassword
            cancel = true
        }

        // Check for a valid user name.
        if (TextUtils.isEmpty(userName)) {
            tvUserName.error = getString(R.string.error_field_required)
            focusView = tvUserName
            cancel = true
        }

        if (cancel) {
            focusView!!.requestFocus()
        } else {
            if (userDataManager.checkUser(userName, password)) {
                val intent = Intent(this, MoviesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                constraintLayoutLogin?.let { Snackbar.make(it, R.string.user_not_registered, Snackbar.LENGTH_SHORT).show() }
            }
            //showProgress(true);
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
}