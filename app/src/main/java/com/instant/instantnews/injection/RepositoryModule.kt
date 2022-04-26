package com.instant.instantnews.injection

import com.instant.instantnews.network.NewsApi
import com.instant.instantnews.repository.NewsApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsApiRepository(
        newsApi: NewsApi
    ) = NewsApiRepository(newsApi)
}