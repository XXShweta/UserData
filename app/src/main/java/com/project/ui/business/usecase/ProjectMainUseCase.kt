package com.project.ui.business.usecase

import com.project.services.ApiResponseCallBack
import com.project.services.retrofitclient.ProjectRetrofitClient
import javax.inject.Inject

class ProjectMainUseCase @Inject constructor(val projectRetrofitClient: ProjectRetrofitClient) {

    fun getPosts(callback: ApiResponseCallBack){
        projectRetrofitClient.getPosts(callback)
    }

    fun getUserInfo(callback: ApiResponseCallBack, userId: Int){
        projectRetrofitClient.getUserInfo(callback,userId)
    }

}