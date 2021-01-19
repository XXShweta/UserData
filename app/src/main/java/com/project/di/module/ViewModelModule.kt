package com.project.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.di.factory.DaggerViewModelFactory
import com.project.di.key.ViewModelKey
import com.project.ui.viewModel.ProjectDetailViewModel
import com.project.ui.viewModel.ProjectMainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
 abstract class ViewModelModule {

    @Binds
    abstract fun bindDaggerViewModelFactory(factoy:DaggerViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(ProjectMainViewModel::class)
    abstract fun projectMainViewModel(projectMainViewModel: ProjectMainViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(ProjectDetailViewModel::class)
   abstract fun projectDetailViewModel(projectDetailViewModel: ProjectDetailViewModel): ViewModel

}