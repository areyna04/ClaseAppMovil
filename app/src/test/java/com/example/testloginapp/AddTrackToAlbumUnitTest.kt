package com.example.testloginapp

import android.app.Application
import com.android.volley.VolleyError
import org.junit.Test
import org.junit.Assert.*
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.repositories.AlbumRepository
import com.example.testloginapp.network.NetworkServiceAdapter
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.mockito.Mockito.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals

class AddTrackToAlbumUnitTest {
    @Test
    fun getAlbumsIsAccesible() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://52.90.82.141:3000/albums")
            .build()

        val response: Response = client.newCall(request).execute()

        assertEquals(200, response.code())
        assertEquals("OK", response.message())
    }
}