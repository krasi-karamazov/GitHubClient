package kpk.dev.presentation.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kpk.dev.domain.usecases.LoginUseCase
import kpk.dev.model.poko.AccessToken
import kpk.dev.model.resource.Resource
import kpk.dev.model.utils.SchedulerProvider
import kpk.dev.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(val loginUseCase: LoginUseCase, override var compositeDisposable: CompositeDisposable, val schedulerProvider: SchedulerProvider): BaseViewModel(compositeDisposable) {

    private val accessTokenLiveData = MutableLiveData<Resource<AccessToken>>()

    fun getAccessToken(code: String): MutableLiveData<Resource<AccessToken>> {
        accessTokenLiveData.value = Resource.Loading()
        compositeDisposable.add(loginUseCase.login(code)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe { data, error ->
                if(error == null) {
                    if(data != null) {
                        accessTokenLiveData.value = Resource.Success(data)
                    }else{
                        accessTokenLiveData.value = Resource.Failure(Throwable("Could not authorize"))
                    }
                }else{
                    accessTokenLiveData.value = Resource.Failure(Throwable("Could not authorize"))
                }
            })
        return accessTokenLiveData
    }
}