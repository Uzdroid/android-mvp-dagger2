package uz.how.simplemvp.view

import uz.how.simplemvp.model.domains.User

/**
 * Created by mirjalol on 6/10/17.
 */

interface ProfileView {

    fun showProgress()

    fun hideProgress()

    fun setProfileData(user: User)

    fun connectionError()

    fun goToLogin()

}

