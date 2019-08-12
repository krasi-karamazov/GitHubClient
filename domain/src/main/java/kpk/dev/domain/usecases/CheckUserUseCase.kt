package kpk.dev.domain.usecases

import io.reactivex.Single
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.poko.User
import kpk.dev.model.repository.UserRepository
import javax.inject.Inject

open class CheckUserUseCase @Inject constructor(var getUserRepository: UserRepository) {

    fun checkUser(token: String, user: String): Single<BaseResponse> {
        return getUserRepository.getUser(token, user)
    }
}