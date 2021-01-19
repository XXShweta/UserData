package com.project.services.di

import com.project.BuildConfig
import com.project.services.retrofitclient.ProjectRetrofitApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiServiceModule {

    @Singleton
    @Provides
    internal fun provideProjectApi(client: OkHttpClient): ProjectRetrofitApi{
        return createRetrofit(BuildConfig.BASE_URL,client, GsonConverterFactory.create())
            .create(ProjectRetrofitApi::class.java)
    }

    private fun createRetrofit(baseUrl : String,
                               okHttpClient: OkHttpClient,
                               gsonConverterFactory: GsonConverterFactory): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }
}