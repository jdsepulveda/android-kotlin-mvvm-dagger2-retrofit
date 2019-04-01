package com.example.app_movies.module

import com.example.app_movies.model.UserDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDataModule {

    @Provides
    @Singleton
    fun userDataManager(): UserDataManager {
        return UserDataManager()
    }
}