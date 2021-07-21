package aosp.sdk.mad.store.remote

import aosp.sdk.mad.store.data.TickerDetail
import aosp.sdk.mad.store.data.Tickers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Polygon {
    @GET("v3/reference/tickers")
    suspend fun tickers(@Query("active") active: Boolean = true,
                @Query("sort") sort: String = "ticker",
                @Query("order") order: String = "asc",
                @Query("limit") limit: Int =20,
                @Query("apiKey") apiKey: String = "XGQouX_bioR6xb6cU4Eorn1upJwGwKxa"): Response<Tickers>

    @GET("v3/reference/tickers")
    suspend fun tickers(@Query("active") active: Boolean = true,
                @Query("sort") sort: String = "ticker",
                @Query("order") order: String = "asc",
                @Query("limit") limit: Int =20,
                @Query("search") search: String,
                @Query("apiKey") apiKey: String = "XGQouX_bioR6xb6cU4Eorn1upJwGwKxa"): Response<Tickers>

    @GET("v3/reference/tickers")
    suspend fun tickers(@Query("cursor") cursor: String,
                @Query("apiKey") apiKey: String = "XGQouX_bioR6xb6cU4Eorn1upJwGwKxa"): Response<Tickers>

    @GET("v1/meta/symbols/{stocksTicker}/company")
    suspend fun tickerDetail(@Path("stocksTicker") stocksTicker: String,
                     @Query("apiKey") apiKey: String = "XGQouX_bioR6xb6cU4Eorn1upJwGwKxa"): Response<TickerDetail>
}