package com.project.services.retrofitclient

import com.project.services.model.response.Post
import com.project.services.model.response.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectRetrofitApi {

    @GET("posts")
    fun getPosts (): Call<List<Post>>

    @GET("users/{userId}")
    fun getUserInfo (@Path("userId") userId : Int ): Call<UserInfo>

}