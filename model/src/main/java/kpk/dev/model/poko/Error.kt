package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Error(
    val locations: List<Location>,
    val message: String,
    val path: List<String>,
    val type: String
) : Parcelable