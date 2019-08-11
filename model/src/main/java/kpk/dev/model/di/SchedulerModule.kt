package kpk.dev.model.di

import dagger.Module
import dagger.Provides
import kpk.dev.model.utils.ApplicationSchedulerProvider
import kpk.dev.model.utils.SchedulerProvider

@Module
class SchedulerModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider{
        return ApplicationSchedulerProvider()
    }
}