package com.example.testloginapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.testloginapp.data.model.Track
import com.example.testloginapp.network.NetworkServiceAdapter
import org.json.JSONObject

class TrackRepository (val application: Application){
    fun refreshData(albumId: Int, callback: (List<Track>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getTracks(albumId,{
            //Guardar los tracks de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    fun postTrackAlbum(body: JSONObject, albumId: Int, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {

        NetworkServiceAdapter.getInstance(application).postTrackAlbum(body,  albumId, {

            callback(it)
        },
            onError
        )
    }


}