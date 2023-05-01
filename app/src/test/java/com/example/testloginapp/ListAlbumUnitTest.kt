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

class ListAlbumTest {
    @Test
    fun getAlbumsReturnsListOfAlbums() {
        val mockApplication = mock(Application::class.java)
        val albumRepository = AlbumRepository(mockApplication)
        val emptyResult: Unit? = null
        val mockCallback: (List<Album>) = emptyList<Album>()
        val mockErrorCallback: (VolleyError) -> Unit = mock()

        albumRepository.refreshData((mockCallback) -> Unit, mockErrorCallback)
    }
}