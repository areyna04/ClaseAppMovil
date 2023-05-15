import org.junit.Test
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import okhttp3.MediaType
import okhttp3.RequestBody
import com.google.gson.JsonParser

class TrackUnitTest {
    private val apiUrl = "http://52.90.82.141:3000"
    private val client = OkHttpClient()
    var idTrack : Int = 0

    @Test
    fun test_getTrack() {
        val request = Request.Builder()
            .url("$apiUrl/albums/4/tracks")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
    @Test
    fun test_postTrack() {
        val requestBody = RequestBody.create(MediaType.parse("application/json"),
            "{\n" +
                "    \"name\": \"Decisiones\",\n" +
                "    \"duration\": \"5:05\"\n" +
                "}")
        val request = Request.Builder()
            .url("$apiUrl/albums/4/tracks")
            .post(requestBody)
            .build()
        val response: Response = client.newCall(request).execute()
        val responseBody = response.body()?.string()
        idTrack = extractValueFromResponse(responseBody)
        assertEquals(200, response.code())
        println(idTrack)
        val request2 = Request.Builder()
            .url("$apiUrl/albums/4/tracks/$idTrack")
            .delete()
            .build()
        val response2: Response = client.newCall(request2).execute()
        assertEquals(204, response2.code())
    }

    private fun extractValueFromResponse(responseBody: String?): Int {
        return JsonParser.parseString(responseBody).asJsonObject.get("id").asInt
    }
}
