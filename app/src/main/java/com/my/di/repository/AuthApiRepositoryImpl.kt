package com.my.di.repository

import com.my.di.model.NetworkResponse
import com.my.di.model.NewsResponse
import com.my.di.network.NewsApiService
import com.my.di.utils.AppConstant
import retrofit2.Response
import javax.inject.Inject


class AuthApiRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : AuthApiRepository {

    override suspend fun getNews(pageNumber: Int, category: String): Response<NewsResponse> {
        return apiService.getNews(AppConstant.COUNTRY_CODE,pageNumber,AppConstant.PAGE_SIZE,category)
    }
}