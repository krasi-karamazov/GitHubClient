package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class RepositoryDetails(
    val `data`: Data
) : Parcelable {
    @Parcelize
    data class Data(
        val repository: Repository
    ) : Parcelable {
        @Parcelize
        data class Repository(
            val closedissues: Closedissues,
            val openissues: Openissues,
            val pullRequests: PullRequests
        ) : Parcelable {
            @Parcelize
            data class PullRequests(
                val totalCount: Int
            ) : Parcelable

            @Parcelize
            data class Openissues(
                val totalCount: Int
            ) : Parcelable

            @Parcelize
            data class Closedissues(
                val totalCount: Int
            ) : Parcelable
        }
    }
}