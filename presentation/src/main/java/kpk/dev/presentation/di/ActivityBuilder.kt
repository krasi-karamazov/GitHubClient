package kpk.dev.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kpk.dev.presentation.auth.view.AuthActivity
import kpk.dev.presentation.repositorydetails.view.RepositoryDetailsActivity
import kpk.dev.presentation.reposlist.view.ReposListActivity
import kpk.dev.presentation.userselect.view.UserSelectActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun bindUserSelectActivity(): UserSelectActivity

    @ContributesAndroidInjector
    abstract fun bindReposListActivity(): ReposListActivity

    @ContributesAndroidInjector
    abstract fun bindRepositoryDetailsActivity(): RepositoryDetailsActivity
}