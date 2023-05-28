package com.example.testloginapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.testloginapp.data.model.Performer
import com.example.testloginapp.network.NetworkServiceAdapter
import org.json.JSONObject

class PerformerRepository (val application: Application){
    fun refreshData(callback: (List<Performer>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getMusicians({
            callback(it)
        },
            onError
        )
    }

    fun getMusician(performerId : Int,  callback: (Performer)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getMusician(performerId,  {
            callback(it)
        },
            onError
        )
    }
}