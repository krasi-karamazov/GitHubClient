package kpk.dev.model.repository

import io.reactivex.Single
import kpk.dev.model.poko.RepositoryDetails

interface IRepoDetailsRepository {
    fun getRepoDetails(token: String, owner: String, repoName: String): Single<RepositoryDetails>
}