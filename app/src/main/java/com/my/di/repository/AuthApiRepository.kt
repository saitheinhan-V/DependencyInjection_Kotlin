package com.my.di.repository

import com.my.di.model.NetworkResponse
import com.my.di.model.NewsData
import com.my.di.model.NewsResponse
import retrofit2.Response

interface AuthApiRepository {

    suspend fun getNews(
        pageNumber: Int,
        category: String
    ): Response<NewsResponse>
}