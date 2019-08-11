package kpk.dev.model.repository

import io.reactivex.Single
import kpk.dev.model.poko.BaseResponse
import kpk.dev.model.poko.User

interface IReposRepository {
    fun getRepos(token: String, user: String, pageSize: Int, endCursorKey: String?): Single<BaseResponse>
}