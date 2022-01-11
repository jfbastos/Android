package br.com.iesb.maximaapp.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class InstanciaRetrofit {

    companion object{
        private val retrofit by lazy{

            val cliente = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()


            Retrofit.Builder()
                .baseUrl(MaximaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build()
        }

        val servicoApi: MaximaApi = retrofit.create(MaximaApi::class.java)

    }

}