package com.alex.recycler_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.recycler_mvvm.model.User
import com.alex.recycler_mvvm.domain.network.Repo

class MainViewModel: ViewModel() {

    val repo = Repo()

    fun fetchUserdata():LiveData<MutableList<User>>{
        val mutableData = MutableLiveData<MutableList<User>>()

        repo.getUserData().observeForever { userList ->
            mutableData.value = userList
        }

        return mutableData
    }
}