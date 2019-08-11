package kpk.dev.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(open var compositeDisposable: CompositeDisposable): ViewModel() {

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}