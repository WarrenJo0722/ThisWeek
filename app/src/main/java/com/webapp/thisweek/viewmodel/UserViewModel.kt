package com.webapp.thisweek.viewmodel

import android.net.Uri
import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.play.integrity.internal.u
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import com.webapp.thisweek.data.api.RetrofitClient
import com.webapp.thisweek.data.model.User
import com.webapp.thisweek.data.repository.FirebaseUserRepository
import com.webapp.thisweek.data.repository.RestUserRepository
import com.webapp.thisweek.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val storage = Firebase.storage

    private val _userList = mutableStateListOf<User>()
    val userList: List<User> = _userList

    var currentUserId by mutableStateOf<String?>(null)
        private set

    val currentUser: User?
        get() = _userList.find { it.id == currentUserId }

    private val userRepository: UserRepository = RestUserRepository()
//    private val userRepository: UserRepository = FirebaseUserRepository()

    init {
        currentUserId = auth.currentUser?.uid

        loadUsers()

        /*db.collection("users").addSnapshotListener { snapshots, exception ->
            if (exception != null || snapshots == null) return@addSnapshotListener
            _userList.clear()
            _userList.addAll(snapshots.toObjects(User::class.java))
        }*/
    }

    fun loadUsers() {
        viewModelScope.launch {
            _userList.clear()
            _userList.addAll(userRepository.getUsers())
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

    fun signUp(user: User, imageUri: Uri?, onFailure: (String) -> Unit = {}, onSuccess: () -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener

                // 사진을 안 골랐으면 바로 프로필 저장
                if (imageUri == null) {
                    saveProfile(uid, user, onFailure, onSuccess)
                    return@addOnSuccessListener
                }

                val imageRef = storage.reference.child("profiles/$uid.jpg")
                imageRef.putFile(imageUri)
                    .addOnSuccessListener {
                        // 업로드 한 사진 다운로드 URL을 받기
                        imageRef.downloadUrl
                            .addOnSuccessListener { url ->
                                saveProfile(uid, user.copy(imageUrl = url
                                    .toString()), onFailure, onSuccess)
                            }
                    }
                    .addOnFailureListener { e ->
                        onFailure(e.message ?: "사진 업로드 실패")
                    }
            }
            .addOnFailureListener { e ->
                Log.e("UserViewModel", "회원가입 실패", e)
                onFailure(e.message ?: "회원가입 실패")
            }
    }

    fun saveProfile(uid: String, user: User, onFailure: (String) -> Unit = {}, onSuccess: () -> Unit) {
        db.collection("users")
            .document(uid)
            .set(user)
            .addOnSuccessListener {
                currentUserId = uid
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("UserViewModel", "유저 저장 실패", e)
                onFailure(e.message ?: "유저 저장 실패")
            }
    }

    fun login(email: String, password: String, onFailure: (String) -> Unit = {}, onSuccess: () -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                currentUserId = result.user?.uid
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("UserViewModel", "로그인 실패", e)
                onFailure(e.message ?: "로그인 실패")
            }
    }

    fun logout() {
        auth.signOut()
        currentUserId = null
    }
}








