package com.example.app_movies.base

import com.example.app_movies.module.UserDataModule
import com.example.app_movies.view.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserDataModule::class])
interface UserComponent {
    fun inject(loginActivity: LoginActivity)
}