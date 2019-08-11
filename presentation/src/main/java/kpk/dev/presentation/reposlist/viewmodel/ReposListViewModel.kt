package kpk.dev.presentation.reposlist.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kpk.dev.domain.usecases.GetReposUseCase
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.resource.Resource
import kpk.dev.model.utils.SchedulerProvider
import kpk.dev.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class ReposListViewModel @Inject constructor(val getReposUseCase: GetReposUseCase, compositeDisposable: CompositeDisposable, val schedulerProvider: SchedulerProvider): BaseViewModel(compositeDisposable) {
    private val reposLiveData = MutableLiveData<Resource<BaseResponse>>()

    fun getRepos(token: String, user: String, pageSize: Int, endCursor: String?): MutableLiveData<Resource<BaseResponse>> {
        compositeDisposable.add(getReposUseCase.getRepos(token, user, pageSize, endCursor)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe { data, error ->
                if(error == null) {
                    if(data != null) {
                        if(data.errors != null && data.errors!!.isNotEmpty()) {
                            reposLiveData.value = Resource.Failure(Throwable(data.errors!![0].message))
                        }else{
                            reposLiveData.value = Resource.Success(data)
                        }
                    }else{
                        reposLiveData.value = Resource.Failure(Throwable("An error occurred"))
                    }
                }else{
                    reposLiveData.value = Resource.Failure(Throwable("An error occurred"))
                }
            })

        return reposLiveData
    }
}