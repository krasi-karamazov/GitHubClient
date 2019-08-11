package kpk.dev.presentation.auth.view

import android.content.Context
import kotlinx.android.synthetic.main.activity_auth.*
import kpk.dev.presentation.R
import kpk.dev.presentation.auth.viewmodel.AuthViewModel
import kpk.dev.presentation.base.view.BaseActivity
import kpk.dev.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.Observer
import kpk.dev.model.resource.Resource
import kpk.dev.model.util.*
import kpk.dev.presentation.userselect.view.UserSelectActivity


class AuthActivity: BaseActivity() {

    private lateinit var viewModel: AuthViewModel

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    override fun initUI() {
        if(getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).contains(TOKEN_PREFS_KEY)) {
            startActivity(Intent(this@AuthActivity, UserSelectActivity::class.java))
            finish()
        }
        viewModel = vmFactory.get()
        btn_auth.setOnClickListener{
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$GIT_HUB_AUTH_URL?client_id=$GIT_HUB_CLIENT_ID&redirect_uri=$GIT_HUB_REDIRECT_URL")
            )
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(GIT_HUB_REDIRECT_URL)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                startAuthProcess(code)
            } else if (uri.getQueryParameter("error") != null) {
                displayError("Could not authorize")
            }
        }
    }

    private fun startAuthProcess(code: String) {
        viewModel.getAccessToken(code).observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    pb_loading.visibility = View.VISIBLE
                    btn_auth.visibility = View.GONE
                }
                is Resource.Failure -> {
                    displayError(it.throwable.message)
                    pb_loading.visibility = View.GONE
                    btn_auth.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).edit().putString(TOKEN_PREFS_KEY, it.data?.accessToken).apply()
                    startActivity(Intent(this@AuthActivity, UserSelectActivity::class.java))
                    finish()
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.activity_auth
}