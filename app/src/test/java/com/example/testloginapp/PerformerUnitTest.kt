import org.junit.Test
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals

class PerformerUnitTest {
    private val apiUrl = "http://52.90.82.141:3000"
    private val client = OkHttpClient()

    @Test
    fun test_getPerformers() {
        val request = Request.Builder()
            .url("$apiUrl/musicians/")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
    @Test
    fun test_getPerformer() {
        val request = Request.Builder()
            .url("$apiUrl/musicians/1")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
}
