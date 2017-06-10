package uz.how.simplemvp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_profile.*
import uz.how.simplemvp.R
import uz.how.simplemvp.model.domains.User
import uz.how.simplemvp.presenter.impl.ProfilePresenterImpl
import uz.how.simplemvp.view.ProfileView
import uz.how.simplemvp.view.fragment.ReposFragment
import javax.inject.Inject

/**
 * Created by mirjalol on 6/9/17.
 */

class ProfileActivity : AppCompatActivity(), ProfileView {

    @Inject
    lateinit var profilePresenter: ProfilePresenterImpl;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profilePresenter.loadProfile()
        tryAgain.setOnClickListener({ profilePresenter.loadProfile() })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {R.id.menu_logout -> {profilePresenter.logout()}}
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setProfileData(user: User) {
        profileName.text = user.name

        Glide.with(this)
                .load(user.avatarUrl)
                .into(profileImage)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.reposContainer, ReposFragment.newInstance())
                .commit()
    }

    override fun connectionError() {
        tryAgain.visibility = View.VISIBLE
    }

    override fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }


    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
    }

}
