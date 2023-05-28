import org.junit.Test
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import okhttp3.MediaType
import okhttp3.RequestBody
import com.google.gson.JsonParser

class AlbumUnitTest {
    private val apiUrl = "http://52.90.82.141:3000"
    private val client = OkHttpClient()
    var idAlbum : Int = 0

    @Test
    fun test_getAlbums() {
        val request = Request.Builder()
            .url("$apiUrl/albums/")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
    @Test
    fun test_getAlbum() {
        val request = Request.Builder()
            .url("$apiUrl/albums/1")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
    @Test
    fun test_postAlbum() {
        val requestBody = RequestBody.create(MediaType.parse("application/json"),
            "{\n" +
                    "    \"name\": \"A Day at the Races\",\n" +
                    "    \"cover\": \"https://www.udiscovermusic.com/wp-content/uploads/2019/11/a-day-at-the-races.jpg\",\n" +
                    "    \"releaseDate\": \"1976-12-10\",\n" +
                    "    \"description\": \"El álbum fue grabado en los Estudios Sarm West, The Manor and Wessex en Inglaterra y con el ingeniero Mike Stone. El título del álbum es una referencia directa al anterior, A Night at the Opera. Ambos álbumes están titulados como películas de los hermanos Marx.\",\n" +
                    "    \"genre\": \"Rock\",\n" +
                    "    \"recordLabel\": \"EMI\"\n" +
                    "}")
        val request = Request.Builder()
            .url("$apiUrl/albums/")
            .post(requestBody)
            .build()
        val response: Response = client.newCall(request).execute()
        val responseBody = response.body()?.string()
        idAlbum = extractValueFromResponse(responseBody)
        assertEquals(200, response.code())
        val request2 = Request.Builder()
            .url("$apiUrl/albums/$idAlbum")
            .delete()
            .build()
        val response2: Response = client.newCall(request2).execute()
        assertEquals(204, response2.code())
    }

    private fun extractValueFromResponse(responseBody: String?): Int {
        return JsonParser.parseString(responseBody).asJsonObject.get("id").asInt
    }
}
