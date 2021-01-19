package com.project.ui.viewModel

import com.project.ui.business.repository.UserDataRepository
import com.project.ui.business.usecase.ProjectMainUseCase
import com.project.ui.fragment.ProjectMainFragment
import com.project.ui.navigator.ProjectMainNavigator
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProjectMainViewModelTest {

    @Mock
    private lateinit var mProjectMainUseCase: ProjectMainUseCase

    @Mock
    private lateinit var mUserDataRepository: UserDataRepository

    val mViewModel by lazy { ProjectMainViewModel(mProjectMainUseCase,mUserDataRepository) }

    @Mock
    private lateinit var mProjectMainFragment: ProjectMainFragment

    @Mock
    private lateinit var mNavigator: ProjectMainNavigator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun navigatorCheckTest(){
        mViewModel.setNavigator(mNavigator)
        Assertions.assertThat(mViewModel.getNavigator()).isNotNull()
    }

    @Test
    fun getPostFromApi_Test(){
        mViewModel.getPostFromApi()
        Assert.assertEquals(mViewModel.getPostFromApi(), mProjectMainUseCase.getPosts(mViewModel))
    }

    @Test
    fun onNoNetwork_Test() {
        mViewModel.setNavigator(mNavigator)
        Assert.assertEquals(mViewModel.onNoNetwork(Mockito.anyString()), mProjectMainFragment.onError(Mockito.anyString()
        ))
    }

    @Test
    fun onError_Test() {
        mViewModel.setNavigator(mNavigator)
        Assert.assertEquals(mViewModel.onError("error", 1), mProjectMainFragment.onError("error"
        ))
    }
}