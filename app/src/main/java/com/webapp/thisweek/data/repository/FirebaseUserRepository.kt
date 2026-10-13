package com.webapp.thisweek.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.webapp.thisweek.data.model.User
import kotlinx.coroutines.tasks.await
import kotlin.jvm.java

class FirebaseUserRepository : UserRepository {
    private val db = Firebase.firestore

    override suspend fun getUsers(): List<User> {
        val snapshots = db.collection("users").get().await()
        return snapshots.toObjects(User::class.java)
    }
}