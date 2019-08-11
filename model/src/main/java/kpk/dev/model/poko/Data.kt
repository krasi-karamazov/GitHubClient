package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

@Parcelize
data class Data(
    val user: User
) : Parcelable