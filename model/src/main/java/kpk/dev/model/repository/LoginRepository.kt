package kpk.dev.model.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kpk.dev.model.datasource.remote.login.LoginApi
import kpk.dev.model.poko.AccessToken
import kpk.dev.model.util.GIT_HUB_CLIENT_ID
import kpk.dev.model.util.GIT_HUB_CLIENT_SECRET
import kpk.dev.model.utils.SchedulerProvider
import javax.inject.Inject

class LoginRepository@Inject constructor(val loginApi: LoginApi): ILoginRepository {
    override fun authorize(code: String): Single<AccessToken> = loginApi.getAccessToken(clientId = GIT_HUB_CLIENT_ID, clientSecret = GIT_HUB_CLIENT_SECRET, code = code, scope = "user repo")
}