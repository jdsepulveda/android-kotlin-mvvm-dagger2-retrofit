package com.example.app_movies.module

import com.example.app_movies.networking.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRepoService(retrofit: Retrofit): APIService {
        return retrofit.create<APIService>(APIService::class.java)
    }
}