package uz.how.simplemvp.presenter.impl

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.how.simplemvp.model.Constants
import uz.how.simplemvp.model.GithubService
import uz.how.simplemvp.model.domains.User
import uz.how.simplemvp.presenter.LoginPresenter
import uz.how.simplemvp.view.LoginView
import uz.how.simplemvp.view.activity.LoginActivity
import javax.inject.Inject

/**
 * Created by mirjalol on 6/9/17.
 */

class LoginPresenterImpl

@Inject
constructor(loginView: LoginActivity, private val githubService: GithubService, private val prefs: SharedPreferences) : LoginPresenter, Callback<User> {
    private val loginView: LoginView
    init {
        this.loginView = loginView
    }

    override fun provideLogin(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            loginView.validationError()
        } else {
            loginView.showProgress()
            githubService.getUser(username).enqueue(this)
        }
    }

    override fun onResponse(call: Call<User>, response: Response<User>) {
        loginView.hideProgress()
        val user = response.body()
        if (user != null && user.id != null) {
            prefs.edit().putString(Constants.LOGIN, user.login).apply()
            loginView.authSuccess()
        } else {
            loginView.authError()
        }
    }

    override fun onFailure(call: Call<User>, t: Throwable) {
        loginView.hideProgress()
        loginView.connectionError()
    }
}
