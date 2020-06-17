package com.dmareca.vintedmvvm.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiAdapter {
//    val apiKey = "c413963fd05d6d7c3b093f87a9137732"

    companion object {
        val LOCALHOST_IP: String  = "10.0.2.2"//" 172.21.3.34
    }

    fun getClientService(): ApiService {

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        val BASE_URL = "http://$LOCALHOST_IP:59273/api/"
        val urlApi = BASE_URL

        val authInterceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()
//                .addQueryParameter("API_KEY", apiKey)
                .addQueryParameter("format", "json")
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()

            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return  retrofit.create(ApiService::class.java)
    }

}