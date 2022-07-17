package suvorov.openweather.presentation.di.module

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import suvorov.openweather.data.api.ApiService
import suvorov.openweather.util.Constants
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val newUrl = chain.request().url.newBuilder()
                .addQueryParameter("units", "metric")
                .build()

            val newRequest = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", Constants.API_KEY)
                .url(newUrl).build()

            chain.proceed(newRequest)
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}