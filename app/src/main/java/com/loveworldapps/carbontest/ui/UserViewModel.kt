package com.loveworldapps.carbontest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.loveworldapps.data.UserRepositoryImpl
import com.loveworldapps.domain.model.User
import com.loveworldapps.carbontest.api.dataSource.UserBoundaryCallback
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by manuelchris-ogar on 27/03/2021.
 */
class UserViewModel( val userRepository: UserRepositoryImpl):ViewModel() {

    lateinit var storedUsers : LiveData<PagedList<User>>

    val selectedUser: MutableLiveData<User> = MutableLiveData()

    var progressLoadStatus: LiveData<String> = MutableLiveData()
        private set
    private val compositeDisposable = CompositeDisposable()


//    initialize paging
    private fun initializePaging() {
    val userBoundaryCallback = UserBoundaryCallback(
            userRepository
    )

    val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(10)
            .setPageSize(10).build()

    storedUsers = LivePagedListBuilder(
            userRepository.getAllUsers(),
            pagedListConfig
    ).setBoundaryCallback(userBoundaryCallback).build()


            progressLoadStatus = userBoundaryCallback.progressLiveStatus



    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    init {
        initializePaging()
    }
}