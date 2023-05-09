package com.example.testloginapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.network.NetworkServiceAdapter
import org.json.JSONObject
class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    fun postAlbum(body: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {

        NetworkServiceAdapter.getInstance(application).postAlbum(body,  {

            callback(it)
        },
            onError
        )
    }

    fun getAlbum(albumId : Int,  callback: (Album)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbum(albumId,  {
            callback(it)
        },
            onError
        )
    }
}