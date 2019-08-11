package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Node(
    val defaultBranchRef: DefaultBranchRef,
    val id: String,
    val isPrivate: Boolean,
    val name: String,
    val owner: Owner,
    val url: String
) : Parcelable