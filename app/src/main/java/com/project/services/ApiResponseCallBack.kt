package com.project.services

interface ApiResponseCallBack {

    fun onSuccess(value: Any)
    fun onError(error: String, errorCode: Int)
    fun onNoNetwork(error: String)
}