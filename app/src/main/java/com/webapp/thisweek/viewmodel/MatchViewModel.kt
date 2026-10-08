package com.webapp.thisweek.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.webapp.thisweek.data.model.Application
import com.webapp.thisweek.data.model.Place
import com.webapp.thisweek.data.model.User
import com.webapp.thisweek.data.model.Weekday

class MatchViewModel : ViewModel() {
    private val db = Firebase.firestore

    var week by mutableStateOf(1)
        private set

    private val _applicationList = mutableStateListOf<Application>()
    val applicationList: List<Application> = _applicationList

    init {
        db.collection("applications").addSnapshotListener { snapshots, exception ->
            if (exception != null || snapshots == null) return@addSnapshotListener
            _applicationList.clear()
            _applicationList.addAll(snapshots.toObjects(Application::class.java))
        }
    }

    fun apply(
        userId: String,
        place: Place,
        weekday: Weekday,
        onFailure: (String) -> Unit = {},
        onSuccess: () -> Unit
    ) {
        val application = Application(
            userId = userId,
            week = week,
            place = place,
            weekday = weekday,
        )

        db.collection("applications")
            .document("${week}_${userId}")
            .set(application)
            .addOnSuccessListener {
                Log.d("MatchViewModel", "저장 성공: ${week}_${userId}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("MatchViewModel", "저장 실패", e)
                onFailure(e.message ?: "저장 실패")
            }
    }
}






