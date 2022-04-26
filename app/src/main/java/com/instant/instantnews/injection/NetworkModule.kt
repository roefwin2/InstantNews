package com.instant.instantnews.injection

import android.util.Log
import com.instant.instantnews.network.NewsApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        httpClient: OkHttpClient,
        moshi: Moshi
    ): NewsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).failOnUnknown())
            .build()
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkhttpBuilder(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor {
            //TODO create TAG
            Log.d("", it)
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverter(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
}