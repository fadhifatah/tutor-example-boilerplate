package com.example.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reminder(

	@field:SerializedName("created_at")
	val createdAt: String?,

	@field:SerializedName("title")
	val title: String?
) : Parcelable
