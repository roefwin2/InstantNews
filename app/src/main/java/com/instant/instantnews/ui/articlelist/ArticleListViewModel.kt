package com.instant.instantnews.ui.articlelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.instantnews.network.models.NetworkNewsApiResponse
import com.instant.instantnews.repository.NewsApiRepository
import com.instant.instantnews.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel@Inject constructor(
    private val repository: NewsApiRepository
) : ViewModel() {
    private val _listScreenState : MutableStateFlow<Resource<NetworkNewsApiResponse>> = MutableStateFlow(Resource.Loading)
    val listScreenState : StateFlow<Resource<NetworkNewsApiResponse>> get() = _listScreenState

    fun fetchListData(){
        viewModelScope.launch {
            repository.getArticles().collect{
                _listScreenState.value = it
            }
        }
    }
}
