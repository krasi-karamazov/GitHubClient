package kpk.dev.presentation.repositorydetails.view

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_repo_details.*
import kpk.dev.model.poko.RepositoryDetails
import kpk.dev.model.resource.Resource
import kpk.dev.model.util.SHARED_PREFS_NAME
import kpk.dev.model.util.TOKEN_PREFS_KEY
import kpk.dev.presentation.R
import kpk.dev.presentation.base.view.BaseActivity
import kpk.dev.presentation.repositorydetails.viewmodel.RepositoryDetailsViewModel
import kpk.dev.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class RepositoryDetailsActivity: BaseActivity() {

    companion object{
        const val REPO_NAME = "reponame"
        const val USER_NAME = "user"
    }

    private lateinit var token: String
    private var user: String? = null
    private var repoName: String? = null

    private lateinit var viewModel: RepositoryDetailsViewModel

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    private val repositoryDetailsObserver = Observer<Resource<RepositoryDetails>>{
        when (it) {
            is Resource.Loading -> {
                pb_loading.visibility = View.VISIBLE
            }
            is Resource.Failure -> {
                displayError(it.throwable.message)
                pb_loading.visibility = View.GONE
            }
            is Resource.Success -> {
                pb_loading.visibility = View.GONE
                renderData(it.data?.data?.repository)
            }
        }
    }

    private fun renderData(repository: RepositoryDetails.Data.Repository?) {
        setData("Unknown source",0, 0, 0)
        repository?.let {
            setData(repoName, repository.openissues.totalCount, repository.closedissues.totalCount, repository.pullRequests.totalCount)
        }
    }

    private fun setData(title: String?, openIssues: Int, closedIssues: Int, pullRequests: Int) {
        tv_repo_title.text = title
        tv_open_issues.text = openIssues.toString()
        tv_closed_issues.text = closedIssues.toString()
        tv_pull_requests.text = pullRequests.toString()
    }

    private fun fetchData(user: String, repoName: String) {
        token = "Bearer "+ getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
            .getString(TOKEN_PREFS_KEY, "")!!
        viewModel.getRepoDetails(token, user, repoName)
            .observe(this, repositoryDetailsObserver)
    }


    override fun initUI() {
        viewModel = vmFactory.get()
        user = intent.extras?.getString(USER_NAME)
        repoName = intent.extras?.getString(REPO_NAME)
        fetchData(user!!, repoName!!)

    }

    override fun getLayoutId(): Int = R.layout.activity_repo_details
}