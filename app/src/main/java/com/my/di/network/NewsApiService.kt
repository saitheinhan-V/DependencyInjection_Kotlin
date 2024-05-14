package com.my.di.network

import com.my.di.model.NewsResponse
import com.my.di.utils.AppConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("X-Api-Key: ${AppConstant.API_KEY}")
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String ?= AppConstant.COUNTRY_CODE,
        @Query("page")
        pageNumber: Int ?= 1,
        @Query("pageSize")
        pageSize: Int ?= AppConstant.PAGE_SIZE,
//        @Query("apiKey")
//        apiKey: String ?= AppConstant.API_KEY
        @Query("category")
        category: String ?= "business"
    ): Response<NewsResponse>
}