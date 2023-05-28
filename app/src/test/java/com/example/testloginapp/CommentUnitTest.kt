import org.junit.Test
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import okhttp3.MediaType
import okhttp3.RequestBody
import com.google.gson.JsonParser

class CommentUnitTest {
    private val apiUrl = "http://52.90.82.141:3000"
    private val client = OkHttpClient()
    var idComment : Int = 0

    @Test
    fun test_getComments() {
        val request = Request.Builder()
            .url("$apiUrl/albums/1/comments")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
    @Test
    fun test_postComment() {
        val requestBody = RequestBody.create(MediaType.parse("application/json"),
            "{\"description\":\"Muy buen album\",\"rating\":\"4\",\"collector\":{\"id\":1}}")
        val request = Request.Builder()
            .url("$apiUrl/albums/1/comments/")
            .post(requestBody)
            .build()
        val response: Response = client.newCall(request).execute()
        val responseBody = response.body()?.string()
        idComment = extractValueFromResponse(responseBody)
        assertEquals(200, response.code())
        val request2 = Request.Builder()
            .url("$apiUrl/albums/1/comments/$idComment")
            .delete()
            .build()
        val response2: Response = client.newCall(request2).execute()
        assertEquals(204, response2.code())
    }

    private fun extractValueFromResponse(responseBody: String?): Int {
        return JsonParser.parseString(responseBody).asJsonObject.get("id").asInt
    }
}
