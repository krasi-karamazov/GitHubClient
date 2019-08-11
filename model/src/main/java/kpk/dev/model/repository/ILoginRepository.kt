package kpk.dev.model.repository

import io.reactivex.Single
import kpk.dev.model.poko.AccessToken

interface ILoginRepository {
    fun authorize(code: String): Single<AccessToken>
}