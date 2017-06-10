package uz.how.simplemvp.presenter.impl

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.how.simplemvp.model.Constants
import uz.how.simplemvp.model.GithubService
import uz.how.simplemvp.model.domains.User
import uz.how.simplemvp.presenter.ProfilePresenter
import uz.how.simplemvp.view.ProfileView
import uz.how.simplemvp.view.activity.ProfileActivity
import javax.inject.Inject

/**
 * Created by mirjalol on 6/10/17.
 */

class ProfilePresenterImpl
@Inject
constructor(profileView: ProfileActivity, private val githubService: GithubService, private val prefs: SharedPreferences)
    : ProfilePresenter, Callback<User> {

    private val profileView: ProfileView
    init {
        this.profileView = profileView
    }
      override fun loadProfile() {
        profileView.showProgress()
        githubService.getUser(prefs.getString(Constants.LOGIN, "")!!).enqueue(this)
    }

    override fun logout() {
        prefs.edit().remove(Constants.LOGIN).apply()
        profileView.goToLogin()
    }

    override fun onResponse(call: Call<User>, response: Response<User>) {
        profileView.hideProgress()
        val user = response.body()
        if (user != null) {
            profileView.setProfileData(user)
        } else {
            profileView.connectionError()
        }
    }

    override fun onFailure(call: Call<User>, t: Throwable) {
        profileView.hideProgress()
        profileView.connectionError()
    }
}
