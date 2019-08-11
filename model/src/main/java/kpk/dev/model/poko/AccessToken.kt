package kpk.dev.model.poko

import com.google.gson.annotations.SerializedName


data class AccessToken(
    @SerializedName("access_token")
    val accessToken: String,
                       @SerializedName("token_type")
                       var type: String){


    fun getTokenType(): String {
        if (!Character.isUpperCase(type[0])) {
            type = type[0].toString()
                .toUpperCase() + type.substring(1)
        }

        return type
    }
}