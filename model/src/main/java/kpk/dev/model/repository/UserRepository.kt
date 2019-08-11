package kpk.dev.model.repository

import io.reactivex.Single
import kpk.dev.model.datasource.remote.graphql.GitHubGraphQLAPI
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.query.UserQuery
import kpk.dev.model.utils.SchedulerProvider
import javax.inject.Inject

open class UserRepository @Inject constructor(val gitHubApi: GitHubGraphQLAPI): IUserRepository {
    override fun getUser(token: String, user: String): Single<BaseResponse> {
        return gitHubApi.checkUser(token, UserQuery(user))

    }
}