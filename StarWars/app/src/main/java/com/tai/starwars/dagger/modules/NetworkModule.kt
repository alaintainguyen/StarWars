package com.tai.starwars.dagger.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tai.starwars.modules.utils.BASE_URL

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder().addHeader("Accept", "application/json")
            val request = requestBuilder.method(original.method(), original.body()).build()
            chain.proceed(request)
        }.build()
    }

    @Provides
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
    }

}
