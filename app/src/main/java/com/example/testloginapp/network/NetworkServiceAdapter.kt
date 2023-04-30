package com.example.testloginapp.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.data.model.Track

class NetworkServiceAdapter constructor(context: Context) {

    companion object{
        //const val BASE_URL= "http://52.90.82.141:3000/"
        const val BASE_URL= "http://localhost:3000/"

        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }



    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    fun getTracks( albumId :Int,  onComplete:(resp:List<Track>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums/$albumId/tracks",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Track>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    //list.add(Track(trackId = item.getInt("id"),name = item.getString("name"), duration = item.getString("duration")))
                    list.add(i, Track(albumId = albumId,name = item.getString("name"), duration = item.getString("duration")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }


    fun postAlbum(body: JSONObject,   onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums",
            body,
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }

    fun postTrackAlbumes(body: JSONObject,  albumId :Int ,   onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums/$albumId/tracks",
            body,
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }


    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}