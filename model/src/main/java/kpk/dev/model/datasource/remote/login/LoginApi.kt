package kpk.dev.model.datasource.remote.login

import io.reactivex.Single
import kpk.dev.model.poko.AccessToken
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header


interface LoginApi {
    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    fun getAccessToken(@Header("Accept")acceptHeader: String="application/json", @Field("client_id")clientId: String, @Field("client_secret") clientSecret: String, @Field("code") code: String, @Field("scope") scope: String): Single<AccessToken>
}