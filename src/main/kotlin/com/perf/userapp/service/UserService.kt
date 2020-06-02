package com.perf.userapp.service

import com.perf.userapp.model.User

interface UserService {
    fun getAllUsers(): List<User?>?

    fun getUserById(userId: String?): User?

    fun addNewUser(user: User?): User?

    fun getAllUserSettings(userId: String?): Map<String?, String?>?

    fun getUserSetting(userId: String?, key: String?): String?

    fun addUserSetting(userId: String?, key: String?, value: String?): String?
}