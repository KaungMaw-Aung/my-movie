package com.kaungmaw.mymovie.network

import com.kaungmaw.mymovie.dto.MovieDto
import com.kaungmaw.mymovie.dto.MovieResponseDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

val okHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(httpLoggingInterceptor)
}.build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface MovieServices {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponseDto>

}

object MovieService {
    val movieServices: MovieServices by lazy {
        retrofit.create(MovieServices::class.java)
    }
}