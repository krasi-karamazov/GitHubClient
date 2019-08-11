package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class User(
    val repositories: Repositories,
    val name: String
) : Parcelable