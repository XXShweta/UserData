package com.project.di.builder

import com.project.di.module.FragmentModule
import com.project.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindActivity(): MainActivity
}