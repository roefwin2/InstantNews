package com.instant.instantnews.ui.articlelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.instantnews.network.models.NetworkNews
import com.instant.instantnews.network.models.NetworkNewsApiResponse
import com.instant.instantnews.repository.NewsApiRepository
import com.instant.instantnews.usecases.FetchArticleListUsecase
import com.instant.instantnews.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel@Inject constructor(
    private val fetchArticleListUsecase: FetchArticleListUsecase
) : ViewModel() {
    private val _listScreenState : MutableStateFlow<Resource<List<NetworkNews>>> = MutableStateFlow(Resource.Loading)
    val listScreenState : StateFlow<Resource<List<NetworkNews>>> get() = _listScreenState

    fun fetchListData(){
        viewModelScope.launch() {
            fetchArticleListUsecase.invoke().collect{
                _listScreenState.value = it
            }
        }
    }
}
