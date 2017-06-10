package uz.how.simplemvp.view

import uz.how.simplemvp.model.domains.Repo

/**
 * Created by mirjalol on 6/10/17.
 */

interface ReposView {

    fun showProgress()
    fun hideProgress()
    fun setRepoList(repoList: List<Repo>)

}
