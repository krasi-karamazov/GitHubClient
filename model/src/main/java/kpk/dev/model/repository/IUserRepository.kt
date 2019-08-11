package kpk.dev.model.repository

import io.reactivex.Single
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.poko.User

interface IUserRepository {
    fun getUser(token: String, user: String): Single<BaseResponse>
}