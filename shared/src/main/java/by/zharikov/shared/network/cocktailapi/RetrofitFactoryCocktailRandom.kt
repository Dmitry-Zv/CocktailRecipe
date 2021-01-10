package by.zharikov.shared.network.cocktailapi

import by.zharikov.shared.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

class RetrofitFactoryCocktailRandom {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(interceptor)
        }
    }
        .build()

    fun getApi(): CocktailRandomAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .client(client)
            .build()
        return retrofit.create()
    }
}