package kpk.dev.presentation.repositorydetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kpk.dev.domain.usecases.GetRepoDetailsUseCase
import kpk.dev.model.poko.RepositoryDetails
import kpk.dev.model.resource.Resource
import kpk.dev.model.utils.SchedulerProvider
import kpk.dev.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class RepositoryDetailsViewModel @Inject constructor(val getRepoDetailsUseCase: GetRepoDetailsUseCase, override var compositeDisposable: CompositeDisposable, val schedulerProvider: SchedulerProvider): BaseViewModel(compositeDisposable) {
    private val repoDetailsLiveData = MutableLiveData<Resource<RepositoryDetails>>()

    fun getRepoDetails(token: String, owner: String, repoName: String): LiveData<Resource<RepositoryDetails>> {
        repoDetailsLiveData.value = Resource.Loading()

        compositeDisposable.add(getRepoDetailsUseCase.getRepoDetails(token, owner, repoName)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { data, error ->
                if(error == null) {
                    if(data != null) {
                        repoDetailsLiveData.value = Resource.Success(data)
                    }else{
                        repoDetailsLiveData.value = Resource.Failure(Throwable("Could not authorize"))
                    }
                }else{
                    repoDetailsLiveData.value = Resource.Failure(Throwable("Could not authorize"))
                }
            })

        return repoDetailsLiveData
    }
}