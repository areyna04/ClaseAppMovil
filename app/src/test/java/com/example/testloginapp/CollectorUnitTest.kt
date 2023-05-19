import org.junit.Test
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals

class CollectorUnitTest {
    private val apiUrl = "http://52.90.82.141:3000"
    private val client = OkHttpClient()

    @Test
    fun test_getCollectors() {
        val request = Request.Builder()
            .url("$apiUrl/collectors/")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
    @Test
    fun test_getCollector() {
        val request = Request.Builder()
            .url("$apiUrl/collectors/1")
            .build()
        val response: Response = client.newCall(request).execute()
        assertEquals(200, response.code())
    }
}
