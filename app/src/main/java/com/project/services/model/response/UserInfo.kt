package com.project.services.model.response

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("id")
    val userId: Int?,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("name")
    val name: String?

    )