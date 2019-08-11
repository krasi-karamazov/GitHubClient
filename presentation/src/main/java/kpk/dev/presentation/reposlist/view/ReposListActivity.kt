package kpk.dev.presentation.reposlist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_repos_list.*
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.resource.Resource
import kpk.dev.model.util.SHARED_PREFS_NAME
import kpk.dev.model.util.TOKEN_PREFS_KEY
import kpk.dev.presentation.R
import kpk.dev.presentation.base.view.BaseActivity
import kpk.dev.presentation.repositorydetails.view.RepositoryDetailsActivity
import kpk.dev.presentation.reposlist.adapter.ReposAdapter
import kpk.dev.presentation.reposlist.viewmodel.ReposListViewModel
import kpk.dev.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class ReposListActivity: BaseActivity() {

    companion object{
        const val USER_INTENT_KEY = "user"
    }

    private lateinit var viewModel: ReposListViewModel

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    private val reposAdapter: ReposAdapter by lazy {
        ReposAdapter {
            itemClicked(it)
        }
    }
    private var isLoading = false
    var user: String? = null
    lateinit var token: String
    var endCursor: String? = null
    var hasNextPage: Boolean? = null


    private val reposObserver: Observer<Resource<BaseResponse>> = Observer {
        when (it) {
            is Resource.Loading -> {
                if(endCursor == null) {
                    pb_loading.visibility = View.VISIBLE
                }
                else{
                    pb_load_more.visibility = View.VISIBLE
                }

            }
            is Resource.Failure -> {
                displayError(it.throwable.message)
                pb_loading.visibility = View.GONE
                pb_load_more.visibility = View.GONE
                isLoading = false

            }
            is Resource.Success -> {
                isLoading = false
                pb_loading.visibility = View.GONE
                pb_load_more.visibility = View.GONE
                this.endCursor = it.data?.responseData?.user?.repositories?.pageInfo?.endCursor
                this.hasNextPage = it.data?.responseData?.user?.repositories?.pageInfo?.hasNextPage
                reposAdapter.updateData(it.data?.responseData?.user?.repositories?.nodes)
            }
        }
    }


    override fun initUI() {
        viewModel = vmFactory.get()
        user = intent.extras?.getString(USER_INTENT_KEY)
        fetchData(user!!, 10, null)
        rv_repos_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_repos_list.adapter = reposAdapter
        rv_repos_list.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = rv_repos_list.layoutManager as LinearLayoutManager

                if (!isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == reposAdapter.itemCount - 1 && hasNextPage != null && hasNextPage == true) {
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    fun loadMore() {
        pb_load_more.visibility = View.VISIBLE
        fetchData(user!!, 10, endCursor)
    }

    private fun fetchData(user: String, pageSize: Int, endCursor: String?) {
        token = "Bearer "+ getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
            .getString(TOKEN_PREFS_KEY, "")!!
        viewModel.getRepos(token, user, pageSize, endCursor)
            .observe(this, reposObserver)
    }

    override fun getLayoutId(): Int = R.layout.activity_repos_list

    private fun itemClicked(name: String) {
        val args = Bundle()
        args.putString(RepositoryDetailsActivity.REPO_NAME, name)
        args.putString(RepositoryDetailsActivity.USER_NAME, user)
        val intent = Intent(this@ReposListActivity, RepositoryDetailsActivity::class.java)
        intent.putExtras(args)
        startActivity(intent)
    }
}