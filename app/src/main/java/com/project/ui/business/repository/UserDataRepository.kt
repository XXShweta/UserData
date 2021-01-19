package com.project.ui.business.repository

import com.project.services.model.response.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(){
    var postList : ArrayList<Post>?= null
    var userId: Int?= null
}