package com.instant.instantnews.usecases

import com.instant.instantnews.repository.NewsApiRepository
import com.instant.instantnews.ui.models.NewsModel
import com.instant.instantnews.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchArticleListUsecase@Inject constructor(private val repository: NewsApiRepository) {

    suspend fun invoke() : Flow<Resource<List<NewsModel>>> {
       return repository.getArticles().map {
          when(it){
              is Resource.Error -> it
              Resource.Loading -> it
              is Resource.Success -> Resource.Success(it.value)
              else -> Resource.Error("null list of networkNews")
          }
      }
    }
}