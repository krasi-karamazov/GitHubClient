package kpk.dev.model.query

import com.google.gson.annotations.SerializedName

class UserQuery(val user: String) : Query() {
    @SerializedName("query")
    val query = "query{\n" +
            "    user(login: \"$user\") {\n" +
            "        name\n" +
            "    }\n" +
            "}"
}