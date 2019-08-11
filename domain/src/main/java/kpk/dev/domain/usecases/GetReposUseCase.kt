package kpk.dev.domain.usecases

import io.reactivex.Single
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.poko.User
import kpk.dev.model.repository.ReposRepository
import javax.inject.Inject

class GetReposUseCase @Inject constructor(val reposRepository: ReposRepository) {

    /**This is where I would usually use a Converter to map the response
     * to something more meaningful and streamlined to be used in the UI (presentation) layer.
     * The current response is lean enough to be handed over without modification
     */

    fun getRepos(token: String, user: String, pageSize: Int, endCursor: String?): Single<BaseResponse> {
        return reposRepository.getRepos(token, user, pageSize, endCursor)
    }
}