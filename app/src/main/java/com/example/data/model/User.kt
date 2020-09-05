package com.example.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("message")
    val message: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("status")
    val status: String?
) : Parcelable