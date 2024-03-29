package kpk.dev.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import kpk.dev.domain.usecases.CheckUserUseCase
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.resource.Resource
import kpk.dev.model.utils.SchedulerProvider
import kpk.dev.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class UserSelectViewModel @Inject constructor(val checkUserUseCase: CheckUserUseCase, override var compositeDisposable: CompositeDisposable, val schedulerProvider: SchedulerProvider): BaseViewModel(compositeDisposable) {
    //just for testing
    private val checkUserLiveData = MutableLiveData<Resource<BaseResponse>>()
    fun checkUser(token: String, user: String): MutableLiveData<Resource<BaseResponse>> {

        compositeDisposable.add(checkUserUseCase.checkUser(token, user)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { data, error ->
                if(error == null) {
                    if(data != null) {
                        if(data.errors != null && data.errors!!.isNotEmpty()) {
                            checkUserLiveData.value = Resource.Failure(Throwable(data.errors?.get(0)?.message?.trim()))
                        }else{
                            checkUserLiveData.value = Resource.Success(data)
                        }
                    }else{
                        checkUserLiveData.value = Resource.Failure(Throwable("An error occurred"))
                    }
                }else{
                    checkUserLiveData.value = Resource.Failure(Throwable("An error occurred"))
                }
            })

        return checkUserLiveData
    }

}