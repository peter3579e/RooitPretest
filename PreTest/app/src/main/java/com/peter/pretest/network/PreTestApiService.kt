package com.peter.pretest.network

import com.peter.pretest.BuildConfig
import com.peter.pretest.data.Articles
import com.peter.pretest.data.News

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://newsapi.org/v2/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

/**
 * A public interface that exposes the [getNews] methods
 */
interface PreTestApiService {

    /**
     * Returns a Coroutine [Deferred] [Articles] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "top-headlines" endpoint will be requested with the GET HTTP method
     */

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") input: String? = null,
        @Query("apiKey") apiKey: String? = null
    ): Articles


}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object PreTestApi {
    val retrofitService: PreTestApiService by lazy { retrofit.create(PreTestApiService::class.java) }
}