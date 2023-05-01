package com.example.testloginapp

import android.app.Application
import com.android.volley.VolleyError
import org.junit.Test
import org.junit.Assert.*
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.repositories.AlbumRepository
import com.example.testloginapp.network.NetworkServiceAdapter
import com.android.volley.toolbox.*
import okhttp3.*
import org.json.JSONArray
import org.mockito.Mockito.*
import org.junit.Assert.assertEquals

class CreateAlbumUnitTest {
    @Test
    fun createAlbumWorks() {

        val client = OkHttpClient()
        val requestBody = RequestBody.create(MediaType.parse("application/json"), "{\n    \"name\": \"Buscando América\",\n    \"cover\": \"https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg\",\n    \"releaseDate\": \"1984-08-01T00:00:00-05:00\",\n    \"description\": \"Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.\",\n    \"genre\": \"Salsa\",\n    \"recordLabel\": \"Elektra\"\n}")

        val request = Request.Builder()
            .url("http://52.90.82.141:3000/albums")
            .post(requestBody)
            .build()

        val response: Response = client.newCall(request).execute()

        assertEquals(200, response.code())
        assertEquals("OK", response.message())
    }
}