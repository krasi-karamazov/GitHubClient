package kpk.dev.model.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kpk.dev.model.datasource.remote.graphql.GitHubGraphQLAPI
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.query.ReposQuery
import kpk.dev.model.utils.SchedulerProvider
import javax.inject.Inject

class ReposRepository @Inject constructor(val gitHubApi: GitHubGraphQLAPI): IReposRepository {

    override fun getRepos(token: String, user: String, pageSize: Int, endCursorKey: String?): Single<BaseResponse> {
        return gitHubApi.getRepos(token, ReposQuery(pageSize, user, endCursorKey))
    }
}