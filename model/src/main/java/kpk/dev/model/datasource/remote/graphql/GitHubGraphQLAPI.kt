package kpk.dev.model.datasource.remote.graphql

import io.reactivex.Single
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.poko.RepositoryDetails
import kpk.dev.model.query.RepoDetailsQuery
import kpk.dev.model.query.ReposQuery
import kpk.dev.model.query.UserQuery
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GitHubGraphQLAPI {
    @POST("/graphql")
    fun getRepos(@Header("Authorization") token: String, @Body query: ReposQuery): Single<BaseResponse>

    @POST("/graphql")
    fun checkUser(@Header("Authorization") token: String, @Body query: UserQuery): Single<BaseResponse>

    @POST("/graphql")
    fun getRepoDetails(@Header("Authorization") token: String, @Body query: RepoDetailsQuery): Single<RepositoryDetails>
}