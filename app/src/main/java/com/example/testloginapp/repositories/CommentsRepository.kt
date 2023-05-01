package com.example.testloginapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.testloginapp.data.model.Comment
import com.example.testloginapp.network.NetworkServiceAdapter

class CommentsRepository (val application: Application){
    fun refreshData(albumId: Int, callback: (List<Comment>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getComments(albumId,{
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}