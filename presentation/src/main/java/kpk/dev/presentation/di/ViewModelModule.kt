package kpk.dev.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kpk.dev.presentation.auth.viewmodel.AuthViewModel
import kpk.dev.presentation.repositorydetails.viewmodel.RepositoryDetailsViewModel
import kpk.dev.presentation.reposlist.viewmodel.ReposListViewModel
import kpk.dev.presentation.viewmodel.UserSelectViewModel
import kpk.dev.presentation.viewmodelfactory.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindContentListViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserSelectViewModel::class)
    internal abstract fun bindUserSelectViewModel(viewModel: UserSelectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReposListViewModel::class)
    internal abstract fun bindReposListViewModel(viewModel: ReposListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailsViewModel::class)
    internal abstract fun bindRepoDetailsViewModel(viewModel: RepositoryDetailsViewModel): ViewModel

    //Add more ViewModels here
}