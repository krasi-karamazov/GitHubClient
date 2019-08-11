package kpk.dev.model.poko


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class BaseResponse(
    @SerializedName("data")
    val responseData: Data,
    @SerializedName("errors")
    val errors: List<Error>?
) : Parcelable