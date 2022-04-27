package com.instant.instantnews.ui.articlelist

import com.instant.instantnews.usecases.FetchArticleListUsecase
import com.instant.instantnews.utils.resource.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class ArticleListViewModelTest {

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    private val mockUsecase = mock<FetchArticleListUsecase> {
        onBlocking { it.invoke() }.doReturn(flowOf(Resource.Success(emptyList())))
    }
    private val viewModel  = ArticleListViewModel(mockUsecase)


    @Test
    fun getListScreenState() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchListData() = runBlockingTest{
        assertEquals(Resource.Loading,viewModel.listScreenState.value)

        viewModel.fetchListData()
        val st = viewModel.listScreenState.value
        assertTrue(st is Resource.Success)
    }
}