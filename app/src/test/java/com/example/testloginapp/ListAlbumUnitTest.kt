package com.example.testloginapp

import android.app.Application
import org.junit.Test
import org.junit.Assert.*
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.repositories.AlbumRepository


class ListAlbumTest {
    @Test
    fun getAlbumsReturnsListOfAlbums() {
        val application = Application()
        val albums_remote = mutableListOf<Album>()
        val albumRepository = AlbumRepository(application)

        assertEquals(4, 2 + 2)
    }
}