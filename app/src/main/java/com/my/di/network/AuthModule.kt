package com.my.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthService(
         @RetrofitModule.AuthRetrofit retrofit: Retrofit
    ): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}