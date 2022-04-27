package com.instant.instantnews.usecases

import com.instant.instantnews.repository.NewsApiRepository
import com.instant.instantnews.utils.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class FetchArticleListUsecaseTest {

    private val mockRepo = mock<NewsApiRepository>{
        onBlocking { it.getArticles() }.doReturn(flowOf(Resource.Success(emptyList())))
    }

    private val fetchArticleListUsecase = FetchArticleListUsecase(mockRepo)

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    operator fun invoke() = runBlockingTest{
       fetchArticleListUsecase.invoke().collect{
           Assert.assertTrue(it is Resource.Success)
       }
    }
}