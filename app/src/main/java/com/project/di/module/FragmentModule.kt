package com.project.di.module

import com.project.ui.fragment.ProjectDetailFragment
import com.project.ui.fragment.ProjectMainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun provideProjectMainFragment(): ProjectMainFragment

    @ContributesAndroidInjector
    abstract fun provideProjectDetailFragment(): ProjectDetailFragment


}

