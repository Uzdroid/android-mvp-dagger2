package uz.how.simplemvp.presenter.impl

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.how.simplemvp.model.Constants
import uz.how.simplemvp.model.GithubService
import uz.how.simplemvp.model.domains.Repo
import uz.how.simplemvp.presenter.ReposPresenter
import uz.how.simplemvp.view.ReposView
import uz.how.simplemvp.view.fragment.ReposFragment
import javax.inject.Inject

/**
 * Created by mirjalol on 6/10/17.
 */

class ReposPresenterImpl
@Inject
constructor(reposView: ReposFragment, private val githubService: GithubService, private val prefs: SharedPreferences) : ReposPresenter, Callback<List<Repo>> {

    private val reposView: ReposView
    init {this.reposView = reposView}

    override fun loadRepos() {
        reposView.showProgress()
        githubService.getRepos(prefs.getString(Constants.LOGIN, "")!!).enqueue(this)
    }

    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
        reposView.hideProgress()
        if (response.body() != null) {
            reposView.setRepoList(response.body()!!)
        }
    }

    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
        reposView.hideProgress()
    }
}
