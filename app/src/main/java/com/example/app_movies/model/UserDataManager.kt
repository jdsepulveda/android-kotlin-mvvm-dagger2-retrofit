package com.example.app_movies.model

import java.util.ArrayList

class UserDataManager {

    private val users = ArrayList<User>()

    init {
        fakeUserData()
    }

    fun checkUser(userName: String, password: String): Boolean {
        var isUser = false

        for (u in users) {
            if (u.userName.equals(userName) && u.userPassword.equals(password)) {
                isUser = true
            }
        }
        return isUser
    }

    private fun fakeUserData() {
        users.add(User("11111", "jhon11", "01111"))
        users.add(User("11112", "david12", "01112"))
        users.add(User("11113", "matthew13", "01113"))
        users.add(User("11114", "sidney14", "01114"))
        users.add(User("11115", "sam15", "01115"))
    }
}