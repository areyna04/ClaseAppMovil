package com.example.testloginapp.viewmodels

import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.testloginapp.data.model.Performer
import com.example.testloginapp.repositories.PerformerRepository

class PerformerDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val performerRepository = PerformerRepository(application)

    private val _performer = MutableLiveData<Performer>()
    val performer: LiveData<Performer>
        get() = _performer

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    fun refreshDataFromNetwork(performerId: Int) {
        performerRepository.getMusician(performerId,
            { performer ->
                _performer.postValue(performer)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }, {
                _eventNetworkError.value = true
            }
        )
    }
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PerformerDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PerformerDetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}