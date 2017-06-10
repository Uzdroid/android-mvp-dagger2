package uz.how.simplemvp.view.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import uz.how.simplemvp.R
import uz.how.simplemvp.model.Constants
import uz.how.simplemvp.presenter.impl.LoginPresenterImpl
import uz.how.simplemvp.view.LoginView
import javax.inject.Inject

/**
 * Created by mirjalol on 6/9/17.
 */

class LoginActivity : AppCompatActivity(), LoginView {

    @Inject
    lateinit var loginPresenter: LoginPresenterImpl
    @Inject
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (prefs.contains(Constants.LOGIN)) {
            ProfileActivity.start(this)
            return
        }
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener({ onLogin() })
    }

    fun onLogin() {
        loginPresenter.provideLogin(
                username.text.toString().trim { it <= ' ' },
                password.text.toString().trim { it <= ' ' })
    }


    override fun validationError() {
        if (username.text.toString().trim { it <= ' ' }.isEmpty()) {
            username.error = "Fill username"
        }
        if (password.text.toString().trim { it <= ' ' }.isEmpty()) {
            password.error = "Fill password"
        }
    }

    override fun authError() {
        showToast(R.string.connection_error)
    }

    override fun authSuccess() {
        ProfileActivity.start(this)
    }

    override fun showProgress() {
        username.isEnabled = false
        password.isEnabled = false
        loginButton.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        username.isEnabled = true
        password.isEnabled = true
        loginButton.isEnabled = true
        progressBar.visibility = View.GONE
    }

    override fun connectionError() {
        showToast(R.string.connection_error)
    }
   
    fun Context.showToast(messageId: Int) {
        Toast.makeText(this,messageId,Toast.LENGTH_SHORT).show();
    }
}

