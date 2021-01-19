package com.project.services.retrofitclient

import com.project.services.ApiResponseCallBack
import com.project.services.model.response.Post
import com.project.services.model.response.UserInfo
import com.project.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProjectRetrofitClient @Inject constructor(
    private val projectRetrofitApi: ProjectRetrofitApi,
    private val internetHelper: InternetHelper
) {

    fun getPosts(callback: ApiResponseCallBack){
        if(internetHelper.isInternetConnected()){
            val call = projectRetrofitApi.getPosts()
            call.enqueue(object : Callback<List<Post>>{
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    t.message?.let {
                        callback.onError(it,500)
                    }                }

                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if(response.code()==200){
                        response.body()?.let {
                            callback.onSuccess(it)
                        }
                    }else{
                        callback.onError(response.message(),response.code())
                    }
                }

            })

        }else{
            callback.onNoNetwork(Constants.NO_INTERNET)
        }
    }

    fun getUserInfo(callback: ApiResponseCallBack, userId:Int){
        if(internetHelper.isInternetConnected()){
            val call = projectRetrofitApi.getUserInfo(userId)
            call.enqueue(object : Callback<UserInfo>{
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    t.message?.let {
                        callback.onError(it,500)
                    }
                }

                override fun onResponse(
                    call: Call<UserInfo>,
                    response: Response<UserInfo>
                ) {

                    if(response.code()==200){
                        response.body()?.let {
                            callback.onSuccess(it)
                        }
                    }else{
                        callback.onError(response.message(),response.code())
                    }

                }

            })

        }else{
            callback.onNoNetwork(Constants.NO_INTERNET)
        }
    }
}