package aosp.sdk.mad

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PolygonService {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.polygon.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val polygon: Polygon = retrofit.create(Polygon::class.java)

    fun getService(): Polygon {
        return polygon
    }
}