package com.example.data.response

import com.example.data.model.Response
import com.example.data.model.User
import com.google.gson.annotations.SerializedName

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */
data class LoginResponse(

    @SerializedName("data") val user: User?

) : Response()