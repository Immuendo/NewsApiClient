package com.mundo.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mundo.newsapiclient.data.model.APIResponse
import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.data.util.Resource
import com.mundo.newsapiclient.domain.usecase.DeleteSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetBusinessArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSearchedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.SaveBusinessArticleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientViewModel(
    private val app: Application,
    private val getBusinessArticleUseCase: GetBusinessArticleUseCase,
    private val getSearchedArticleUseCase: GetSearchedArticleUseCase,
    private val saveBusinessArticleUseCase: SaveBusinessArticleUseCase,
    private val getSavedArticleUseCase: GetSavedArticleUseCase,
    private val deleteSavedArticleUseCase: DeleteSavedArticleUseCase
): AndroidViewModel(app) {
    val businessHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getBusinessHeadlines(country: String, page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            businessHeadlines.postValue(Resource.Loading())
            try {
                if(isNetworkAvailable(app)){
                    val apiResult = getBusinessArticleUseCase.execute(country, page)
                    businessHeadlines.postValue(apiResult)
                } else {
                    businessHeadlines.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception){
                businessHeadlines.postValue(Resource.Error(e.message.toString()))
            }

        }
    }

    val searchedHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getSearchedBusinessHeadlines(country: String, page: Int, query: String){
        viewModelScope.launch(Dispatchers.IO) {
            searchedHeadlines.postValue(Resource.Loading())
            try {
                if(isNetworkAvailable(app)){
                    val apiSearchResult = getSearchedArticleUseCase.execute(country, page, query)
                    searchedHeadlines.postValue(apiSearchResult)
                } else {
                    searchedHeadlines.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception){
                searchedHeadlines.postValue(Resource.Error(e.message.toString()))
            }

        }
    }

    fun saveBusinessArticle(article: Article){
        viewModelScope.launch {
            saveBusinessArticleUseCase.execute(article)
        }
    }

    fun getSavedArticles() = liveData {
        getSavedArticleUseCase.execute().collect{
            emit(it)
        }
    }

    fun deleteSavedArticle(article: Article) = viewModelScope.launch {
            deleteSavedArticleUseCase.execute(article)
        }


    private fun isNetworkAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
        return result
    }
}