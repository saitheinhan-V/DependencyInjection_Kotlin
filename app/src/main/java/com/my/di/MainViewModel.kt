package com.my.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.di.model.NewsData
import com.my.di.model.NewsResponse
import com.my.di.repository.AuthApiRepository
import com.my.di.utils.NetworkHelper
import com.my.di.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authApiRepository: AuthApiRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(){

    private val _news = MutableLiveData<Resource<List<NewsData>>>()
    val news: LiveData<Resource<List<NewsData>>>
        get() = _news

    init {
    }


    fun getNews(pageNumber: Int,category: String) {
        viewModelScope.launch {
            _news.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                authApiRepository.getNews(pageNumber, category).let {
                    if (it.isSuccessful) {
                        _news.postValue(Resource.success(it.body()?.articles))
                    } else _news.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _news.postValue(Resource.error("No internet connection", null))
        }
    }


}