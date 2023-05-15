package com.example.testloginapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.testloginapp.data.model.Track
import com.example.testloginapp.repositories.TrackRepository
import org.json.JSONObject

class TrackViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val trackRepository = TrackRepository(application)

    private val _tracks = MutableLiveData<List<Track>>()

    val tracks: LiveData<List<Track>>
        get() = _tracks

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = albumId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        trackRepository.refreshData(id, {
            _tracks.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }

    fun postDataFromNetwork(postData: JSONObject, albumId: Int) {
        trackRepository.postTrackAlbum(
            postData, albumId,
            {
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },{
                _eventNetworkError.value = true
            })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TrackViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}