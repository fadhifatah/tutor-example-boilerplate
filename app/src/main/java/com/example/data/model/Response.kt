package com.example.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
open class Response {

    @SerializedName("status")
    val status: String? = null

    @SerializedName("code")
    val code: Int? = null

    @SerializedName("message")
    val message: String? = null
}