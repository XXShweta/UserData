package com.project.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.project.services.ApiResponseCallBack
import com.project.services.model.response.Post
import com.project.ui.base.BaseViewModel
import com.project.ui.business.repository.UserDataRepository
import com.project.ui.business.usecase.ProjectMainUseCase
import com.project.ui.navigator.ProjectMainNavigator
import javax.inject.Inject

class ProjectMainViewModel @Inject constructor(val projectMainUseCase: ProjectMainUseCase, val userDataRepository: UserDataRepository): BaseViewModel<ProjectMainNavigator>(),
    ApiResponseCallBack {

    val postList : ArrayList<Post> = ArrayList<Post>()
    val hashMap : LinkedHashMap<Int, ArrayList<Post>> = LinkedHashMap()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    fun getPostFromApi(){
        projectMainUseCase.getPosts(this)
    }

    override fun onSuccess(value: Any) {
        if(value is List<*>){
            hashMap.clear()
            postList.clear()
            postList.addAll(value as ArrayList<Post>)
            insertDataIntoMap(postList)
        }
    }

    private fun insertDataIntoMap(list: ArrayList<Post>) {
        hashMap.clear()
        list.forEach {
            if(hashMap.containsKey(it.userId)){
                val oldList = hashMap.get(it.userId)
                oldList!!.add(it)
                hashMap.put(it.userId!!,oldList)
            }else{
                val newList: ArrayList<Post>  =  ArrayList()
                newList.add(it)
                hashMap.put(it.userId!!,newList)
            }
        }
        isLoading.value= false
    }

    override fun onError(error: String, errorCode: Int) {
        getNavigator()!!.onError(error)
    }

    override fun onNoNetwork(error: String) {
        getNavigator()!!.onError(error)
    }

    fun onItemClick(key: Int){
        val userData : ArrayList<Post> = hashMap.get(key)!!
        userDataRepository.userId = key
        userDataRepository.postList = userData
        getNavigator()!!.goToDetailScreen()
    }

}