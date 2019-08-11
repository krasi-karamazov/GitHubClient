package kpk.dev.model.query

import com.google.gson.Gson

abstract class Query {

    fun toJson() {
        Gson().toJson(this)
    }
}