package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class DefaultBranchRef(
    val name: String
) : Parcelable