package kpk.dev.model.poko

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Errors(
    @SerializedName("errors")
    val errorsList: List<Error>
) : Parcelable