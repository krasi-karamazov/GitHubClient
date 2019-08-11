package kpk.dev.model.di

import android.app.Application
import dagger.Module
import dagger.Provides
import kpk.dev.model.datasource.remote.graphql.GitHubGraphQLAPI
import kpk.dev.model.datasource.remote.login.LoginApi
import kpk.dev.model.util.GIT_HUB_BASE_URL
import kpk.dev.model.util.GIT_HUB_LOGIN_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Provides
    @Singleton
    @Named("graphql_retrofit")
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(GIT_HUB_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @Named("login_retrofit")
    internal fun provideLoginRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(GIT_HUB_LOGIN_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGitHubApi(@Named("graphql_retrofit") retrofit: Retrofit): GitHubGraphQLAPI = retrofit.create(
        GitHubGraphQLAPI::class.java)

    @Provides
    @Singleton
    internal fun provideLoginApi(@Named("login_retrofit") retrofit: Retrofit): LoginApi = retrofit.create(
        LoginApi::class.java)

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptorBody = HttpLoggingInterceptor()
        loggingInterceptorBody.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().cache(null)
            .addInterceptor(loggingInterceptorBody)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }
}