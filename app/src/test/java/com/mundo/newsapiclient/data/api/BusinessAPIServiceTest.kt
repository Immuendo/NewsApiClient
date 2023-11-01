package com.mundo.newsapiclient.data.api

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat

class BusinessAPIServiceTest {
    private lateinit var service: BusinessAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setup(){
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BusinessAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedAsExpected(){
        runBlocking {
            enqueueMockResponse("businessresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&category=business&apiKey=e1db0cc006874bde985461d9d9418d3e")
        }
    }

    @Test
    fun getTopHeadlines_responseReceived_correctPageCount(){
        runBlocking {
            enqueueMockResponse("businessresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_responseReceived_correctContent(){
        runBlocking {
            enqueueMockResponse("businessresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articlesList = responseBody!!.articles
            val article = articlesList[0]
            assertThat(article.author).isEqualTo("Alex Harring, Samantha Subin")
            assertThat(article.title).isEqualTo("Dow slides 200 points with investors on edge before Fed decision: Live updates - CNBC")
            assertThat(article.publishedAt).isEqualTo("2023-09-19T16:20:00Z")
        }
    }





    @After
    fun tearDown() {
        server.shutdown()
    }
}