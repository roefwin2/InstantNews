package com.instant.instantnews.injection

import android.util.Log
import com.instant.instantnews.network.api.NewsApi
import com.instant.instantnews.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
            .baseUrl(Constants.NEWS_API_BASE_URL)
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
    fun provideMoshiConverter(): Moshi =
        // See https://github.com/square/moshi#kotlin
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
}