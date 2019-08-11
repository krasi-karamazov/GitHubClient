package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class PageInfo(
    val endCursor: String,
    val hasNextPage: Boolean
) : Parcelable