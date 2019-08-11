package kpk.dev.presentation.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import kpk.dev.presentation.dialog.BaseDialog
import kpk.dev.presentation.dialog.ErrorDialog
import kpk.dev.presentation.viewmodelfactory.ViewModelFactory

abstract class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initUI()
    }

    protected abstract fun initUI()
    protected abstract fun getLayoutId(): Int

    protected fun displayError(errorMessage: String?) {
        val bundle = Bundle()
        bundle.putString(BaseDialog.TITLE_ARG_KEY, "Error")
        bundle.putString(BaseDialog.MESSAGE_ARG_KEY, errorMessage ?: "An error occurred")
        ErrorDialog.getInstance(bundle).show(supportFragmentManager, ErrorDialog::class.java.simpleName)
    }

    inline fun <reified T: ViewModel> ViewModelFactory.get(): T = androidx.lifecycle.ViewModelProviders.of(this@BaseActivity, this)[T::class.java]
}