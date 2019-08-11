package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Repositories(
    val nodes: List<Node>,
    val pageInfo: PageInfo
) : Parcelable