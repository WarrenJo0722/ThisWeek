package com.webapp.thisweek.data.repository

import com.webapp.thisweek.data.api.RetrofitClient
import com.webapp.thisweek.data.model.User

class RestUserRepository : UserRepository {
    override suspend fun getUsers(): List<User> {
        return RetrofitClient.userApi.getUsers()
    }
}