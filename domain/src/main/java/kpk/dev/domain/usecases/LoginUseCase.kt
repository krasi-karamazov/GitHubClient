package kpk.dev.domain.usecases

import io.reactivex.Single
import kpk.dev.model.poko.AccessToken
import kpk.dev.model.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val loginRepository: LoginRepository) {
    fun login(code: String): Single<AccessToken> =  loginRepository.authorize(code)
}