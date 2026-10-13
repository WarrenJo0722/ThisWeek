package com.webapp.thisweek.data.api

import com.webapp.thisweek.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("api/users")
    suspend fun getUsers(): List<User>

    @POST("api/users")
    suspend fun createUser(@Body user: User): User
}




