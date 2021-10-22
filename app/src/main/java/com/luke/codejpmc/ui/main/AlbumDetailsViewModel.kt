package com.luke.codejpmc.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luke.codejpmc.repository.AlbumRepository
import com.luke.codejpmc.repository.Results
import com.luke.codejpmc.utils.LoadingState
import com.luke.codejpmc.utils.OpenForTesting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@OpenForTesting
class AlbumDetailsViewModel(val albumrepository: AlbumRepository): ViewModel()  {


    private val _albumDetailsEvent = MutableLiveData<AlbumDetailsEvent>()
    val albumDetailsEvent: LiveData<AlbumDetailsEvent>
        get() = _albumDetailsEvent

    private val viewModelJob = SupervisorJob()

    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getAlbumDetails(albumId:Int) {
          viewModelScope.launch {
              _albumDetailsEvent.value = AlbumDetailsEvent.Loading
              when(val result = albumrepository.getAlbumDetails(albumId)) {
                  is Results.Ok -> {
                      _albumDetailsEvent.value = AlbumDetailsEvent.AlbumdetailLoaded(result.data)
                  }
                  is Results.Error -> {
                      _albumDetailsEvent.value = AlbumDetailsEvent.AlbumError
                  }
              }
          }
    }



}