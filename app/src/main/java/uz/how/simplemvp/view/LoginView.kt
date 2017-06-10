package uz.how.simplemvp.view

/**
 * Created by mirjalol on 6/9/17.
 */

interface LoginView {

    fun validationError()

    fun authError()

    fun authSuccess()

    fun showProgress()

    fun hideProgress()

    fun connectionError()

}
