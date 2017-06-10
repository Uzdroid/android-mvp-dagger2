package uz.how.simplemvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.android.synthetic.main.item_repo.*
import uz.how.simplemvp.R
import uz.how.simplemvp.model.domains.Repo
import uz.how.simplemvp.presenter.impl.ReposPresenterImpl
import uz.how.simplemvp.view.ReposView
import javax.inject.Inject

/**
 * Created by mirjalol on 6/10/17.
 */

class ReposFragment : Fragment(), ReposView {

    @Inject
    lateinit var reposPresenter: ReposPresenterImpl
    @Inject
    lateinit var layoutInflater: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_repos, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        reposPresenter.loadRepos()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setRepoList(repoList: List<Repo>) {
        if (repoList.isNotEmpty()) {
            recyclerView.adapter = ReposAdapter(repoList)
        } else {
            placeHolderText.visibility = View.VISIBLE
        }
    }

    inner class ReposAdapter(private val repoList: List<Repo>) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.item_repo, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            viewHolder.onBind(repoList[i])
        }

        override fun getItemCount() = repoList.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun onBind(repo: Repo) {
                with(repo) {
                    repoName.text = repo.name
                    repoFullName.text = repo.fullName
                }
            }
        }

    }

    companion object {
        fun newInstance(): ReposFragment {
            return ReposFragment()
        }
    }

}
