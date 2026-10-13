package com.webapp.thisweek.data.repository

import com.webapp.thisweek.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}