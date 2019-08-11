package kpk.dev.model.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kpk.dev.model.datasource.remote.graphql.GitHubGraphQLAPI
import kpk.dev.model.poko.RepositoryDetails
import kpk.dev.model.query.RepoDetailsQuery
import kpk.dev.model.utils.SchedulerProvider
import javax.inject.Inject

class RepoDetailsRepository @Inject constructor(val gitHubApi: GitHubGraphQLAPI): IRepoDetailsRepository {
    override fun getRepoDetails(token: String, owner: String, repoName: String): Single<RepositoryDetails> {
        return gitHubApi.getRepoDetails(token, RepoDetailsQuery(owner,repoName))
    }

}