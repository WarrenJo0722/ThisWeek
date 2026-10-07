package com.webapp.thisweek.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.webapp.thisweek.data.model.User

class UserViewModel : ViewModel() {
    private val db = Firebase.firestore

    private val _userList = mutableStateListOf<User>()
    val userList: List<User> = _userList

    fun addUser(user: User) {
        db.collection("users").add(user)
    }
}