package com.project.di.component

import android.app.Application
import com.project.ProjectApplication
import com.project.di.builder.ActivityBuilder
import com.project.di.module.AppModule
import com.project.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                        AppModule::class,
                        ActivityBuilder::class,
                        ViewModelModule::class])
interface AppComponent : AndroidInjector<ProjectApplication>{

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}