package com.project.services.retrofitclient

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

class InternetHelper @Inject constructor(val context: Context){

    fun isInternetConnected():Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val nw : Network= connectivityManager.activeNetwork
            if(nw == null)return false
            val acNw : NetworkCapabilities = connectivityManager.getNetworkCapabilities(nw)
            return when{
                acNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
                acNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)-> true
                else-> false
            }
        }else{
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }
}