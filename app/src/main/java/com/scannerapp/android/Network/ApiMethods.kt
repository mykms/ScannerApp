package com.scannerapp.android.Network

import android.content.Context
import com.google.gson.GsonBuilder
import com.scannerapp.android.BuildConfig
import com.scannerapp.android.Model.Product
import com.scannerapp.android.Model.ProductResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiMethods {
    companion object {
        private val BASE_URL: String = "https://catalog.napolke.ru/"
        const val IMAGE_URL: String = "https://img.napolke.ru/image/get?uuid="

        fun getInstance(context: Context): ApiMethods {
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .client(getOkHttpClient(context))

            return  retrofitBuilder.build().create(ApiMethods::class.java)
        }

        private fun getOkHttpClient(context: Context): OkHttpClient {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.interceptors().add(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })

            return httpClient.build()
        }
    }

    @POST("search/catalog")
    fun searchProduct(@Body product: Product): Call<ProductResult>
}