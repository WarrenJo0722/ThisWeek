package com.webapp.thisweek.viewmodel

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.play.integrity.internal.u
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.webapp.thisweek.data.model.User

class UserViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val _userList = mutableStateListOf<User>()
    val userList: List<User> = _userList

    var currentUserId by mutableStateOf<String?>(null)
        private set

    val currentUser: User?
        get() = _userList.find { it.id == currentUserId }

    init {
        currentUserId = auth.currentUser?.uid

        db.collection("users").addSnapshotListener { snapshots, exception ->
            if (exception != null || snapshots == null) return@addSnapshotListener
            _userList.clear()
            _userList.addAll(snapshots.toObjects(User::class.java))
        }
    }

    fun selectUser(user: User) {
        currentUserId = user.id
    }

    fun addUser(user: User, onFailure: (String) -> Unit = {}, onSuccess: () -> Unit) {
        db.collection("users").add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("UserViewModel", "저장 성공: ${documentReference.id}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("UserViewModel", "저장 실패", e)
                onFailure(e.message ?: "저장 실패")
            }
    }

    fun getUserById(userId: String): User? {
        return _userList.find { it.id == userId }
    }

    fun signUp(user: User) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener

                db.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        currentUserId = uid
                    }
            }
    }
}








