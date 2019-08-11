package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Location(
    val column: Int,
    val line: Int
) : Parcelable