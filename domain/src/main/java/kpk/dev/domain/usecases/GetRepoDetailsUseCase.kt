package kpk.dev.domain.usecases

import io.reactivex.Single
import kpk.dev.model.poko.RepositoryDetails
import kpk.dev.model.repository.RepoDetailsRepository
import javax.inject.Inject

class GetRepoDetailsUseCase @Inject constructor(val repoDetailsRepository: RepoDetailsRepository) {
    fun getRepoDetails(token: String, owner: String, repoName: String): Single<RepositoryDetails> = repoDetailsRepository.getRepoDetails(token, owner, repoName)
}