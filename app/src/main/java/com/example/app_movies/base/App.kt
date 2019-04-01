package com.example.app_movies.base

import android.app.Application
import android.content.Context

class App : Application() {

    lateinit var userComponent: UserComponent
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        userComponent = DaggerUserComponent.create()
        appComponent = DaggerAppComponent.create()
    }

    companion object {

        fun getUserComponent(context: Context): UserComponent? {
            return (context.applicationContext as App).userComponent
        }

        fun getAppComponent(context: Context): AppComponent? {
            return (context.applicationContext as App).appComponent
        }
    }
}