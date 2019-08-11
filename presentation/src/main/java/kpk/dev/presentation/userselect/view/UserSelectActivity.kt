package kpk.dev.presentation.userselect.view

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_user_select.*
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.resource.Resource
import kpk.dev.model.util.SHARED_PREFS_NAME
import kpk.dev.model.util.TOKEN_PREFS_KEY
import kpk.dev.presentation.R
import kpk.dev.presentation.base.view.BaseActivity
import kpk.dev.presentation.extensions.addTextWatcher
import kpk.dev.presentation.reposlist.view.ReposListActivity
import kpk.dev.presentation.userselect.viewmodel.UserSelectViewModel
import kpk.dev.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class UserSelectActivity: BaseActivity() {

    @Inject
    internal lateinit var compositeDisposable: CompositeDisposable

    private lateinit var viewModel: UserSelectViewModel

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    override fun getLayoutId(): Int = R.layout.activity_user_select

    private val userObserver: Observer<Resource<BaseResponse>> = Observer {
        when (it) {
            is Resource.Loading -> {
                pb_loading.visibility = View.VISIBLE
                btn_query_repos.isEnabled = false
            }
            is Resource.Failure -> {
                displayError(it.throwable.message)
                pb_loading.visibility = View.GONE
                btn_query_repos.isEnabled = true
            }
            is Resource.Success -> {
                val intent = Intent(this@UserSelectActivity, ReposListActivity::class.java)
                intent.putExtra(ReposListActivity.USER_INTENT_KEY, et_username.text.toString())
                startActivity(intent)
            }
        }
    }

    override fun initUI() {
        viewModel = vmFactory.get()
        et_username.addTextWatcher()
            .subscribe(object: io.reactivex.Observer<Boolean> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: Boolean) {
                    btn_query_repos.isEnabled = t
                }

                override fun onError(e: Throwable) {}

            })
        btn_query_repos.setOnClickListener{ viewModel.checkUser("Bearer "+ getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).getString(
            TOKEN_PREFS_KEY, ""), et_username.text.toString()).observe(this, userObserver)}
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}