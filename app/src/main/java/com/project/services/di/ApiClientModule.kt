package com.project.services.di

import android.content.Context
import com.project.services.retrofitclient.InternetHelper
import com.project.services.retrofitclient.ProjectRetrofitApi
import com.project.services.retrofitclient.ProjectRetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiServiceModule::class])
class ApiClientModule {

    @Provides
    @Singleton
    internal fun provideInternetHelper(context: Context): InternetHelper {
        return InternetHelper(context)
    }

    @Provides
    @Singleton
    internal fun provideApiRetrofitClient(
        context: Context,
        projectRetrofitApi: ProjectRetrofitApi,
        internetHelper: InternetHelper
    ): ProjectRetrofitClient{
        return ProjectRetrofitClient(projectRetrofitApi,internetHelper)
    }

}