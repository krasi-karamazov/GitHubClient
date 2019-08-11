package kpk.dev.githubclient.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kpk.dev.githubclient.application.GItHubClientApplication
import kpk.dev.model.di.NetworkingModule
import kpk.dev.model.di.SchedulerModule
import kpk.dev.presentation.di.ActivityBuilder
import kpk.dev.presentation.di.DisposableModule
import kpk.dev.presentation.di.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelModule::class, DisposableModule::class, ActivityBuilder::class, NetworkingModule::class, SchedulerModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(application: GItHubClientApplication)

}