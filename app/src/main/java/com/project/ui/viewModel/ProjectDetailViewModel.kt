package com.project.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.project.services.ApiResponseCallBack
import com.project.services.model.response.Post
import com.project.services.model.response.UserInfo
import com.project.ui.base.BaseViewModel
import com.project.ui.business.repository.UserDataRepository
import com.project.ui.business.usecase.ProjectMainUseCase
import com.project.ui.navigator.ProjectDetailNavigator
import javax.inject.Inject

class ProjectDetailViewModel @Inject constructor(val projectMainUseCase: ProjectMainUseCase,val userDataRepository: UserDataRepository): BaseViewModel<ProjectDetailNavigator>(),
    ApiResponseCallBack {

    var userName : MutableLiveData<String> = MutableLiveData("")
    var userEmail : MutableLiveData<String> = MutableLiveData("")
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    fun getUserDetailFromApi(){
        projectMainUseCase.getUserInfo(this,userDataRepository.userId!!)
    }

    override fun onSuccess(value: Any) {
        if(value is UserInfo){
            userName.value = value.userName
            userEmail.value = value.email
            isLoading.value = false
        }
        getNavigator()!!.setUIData()
    }

    override fun onError(error: String, errorCode: Int) {
        isLoading.value = false
        getNavigator()!!.onError(error)
    }

    override fun onNoNetwork(error: String) {
        isLoading.value = false
        getNavigator()!!.onError(error)
    }

    fun getListOfPost(): ArrayList<Post>{
        return userDataRepository.postList!!
    }

}